package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MockMvc {
	
	// test_obte_nombre_contactes()
	
	@Autowired
	private AgendaService agenda;

	@RequestMapping(path="/nombre")
	@ResponseBody
	public String numeroDeContactos() {
		int numero = agenda.nombreContactes();
		return String.valueOf(numero);
	}
	
	// test_obte_telefon()
	
	@RequestMapping(path="/telefon")
	@ResponseBody
	String numeroDeTelefono(String id) {
		Persona persona = agenda.recupera(id);
		String numeroPersona = persona.getTelefon();
		return numeroPersona;
	}
}
