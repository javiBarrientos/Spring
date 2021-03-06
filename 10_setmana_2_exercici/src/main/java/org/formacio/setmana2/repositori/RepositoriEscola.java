package org.formacio.setmana2.repositori;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.springframework.stereotype.Component;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */

@Component
public class RepositoriEscola {
	
	@PersistenceContext
	private EntityManager em;
	
	public Curs carregaCurs(String nom) {
		return em.find(Curs.class, nom);
	}
	
	public Matricula apunta(String alumne, String curs) throws EdatIncorrecteException {
		Alumne alumno = em.find(Alumne.class, alumne);
    	Curs curso = em.find(Curs.class, curs);
    	
    	Matricula matricula = new Matricula();
		
	    if (alumno.getEdat() < curso.getEdatMinima()) {
	    	throw new EdatIncorrecteException();
	    } else {
	    	matricula.setAlumne(alumno);
	    	matricula.setCurs(curso);
	    	em.persist(matricula);
	    }
	    return matricula;
	}
		
}
