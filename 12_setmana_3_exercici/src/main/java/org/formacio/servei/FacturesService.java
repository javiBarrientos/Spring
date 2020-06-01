package org.formacio.servei;

import java.util.Optional;

import javax.transaction.Transactional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FacturesService {
	
	@Autowired
	FacturesRepositori repoFactures;
	
	@Autowired
	FidalitzacioService fidelitzacioServei;
	
	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		
		Optional<Factura> factura = repoFactures.findById(idFactura);
		
		if (factura.isPresent()) {
			LiniaFactura liniaFactura = new LiniaFactura();
			liniaFactura.setProducte(producte);
			liniaFactura.setTotal(totalProducte);
			factura.get().getLinies().add(liniaFactura);
			repoFactures.save(factura.get());
			
			notificarPremi(factura.get());
		}
		
		return factura.get();
	}
	
	public void notificarPremi(Factura factura) {
		final int lineasConPremio = 4;
		if (factura.getLinies().size() >= lineasConPremio) {
			fidelitzacioServei.notificaRegal(factura.getClient().getEmail());
		}
	}
}
