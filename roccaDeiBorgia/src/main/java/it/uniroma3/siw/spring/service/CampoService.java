package it.uniroma3.siw.spring.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.repository.CampoRepository;

@Service
public class CampoService {
	
	@Autowired
	private CampoRepository campoRepository; 
	
	@Transactional
	public Campo inserisci(Campo campo) {
		return campoRepository.save(campo);
	}

	@Transactional
	public List<Campo> tutti() {
		return (List<Campo>) campoRepository.findAll();
	}

	@Transactional
	public Campo campoPerId(Long id) {
		Optional<Campo> optional = campoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Campo campo) {
		List<Campo> campi = this.campoRepository.findByMatricola(campo.getMatricola());
		if (campi.size() > 0)
			return true;
		else 
			return false;
	}

	@Transactional
	public void elimina(Campo campo) {
		this.campoRepository.delete(campo);
	}
	
	@Transactional
	public Object campiOrdinatiPerSport() {
		List<Campo> campi = (List<Campo>) this.campoRepository.findAll(1);
		if(campi.size()>0)
			return campi;
		return null;
	}
	
	@Transactional
	public Object campiOrdinatiPerPrezzoOrario() {
		List<Campo> campi = (List<Campo>) this.campoRepository.findAll(1.5);
		if(campi.size()>0)
			return campi;
		return null;
	}

	public long conta() {
		return this.campoRepository.count();
		 
	}
	
//	Collections.shuffle(campi);
//	campi = Arrays.asList(campi.get(0),campi.get(1),campi.get(2));	
}
