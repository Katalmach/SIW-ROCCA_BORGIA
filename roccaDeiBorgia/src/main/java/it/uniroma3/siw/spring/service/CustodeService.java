package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Custode;
import it.uniroma3.siw.spring.repository.CustodeRepository;

@Service
public class CustodeService {
	
	@Autowired
	private CustodeRepository custodeRepository; 
	
	@Transactional
	public Custode inserisci(Custode custode) {
		return custodeRepository.save(custode);
	}

	@Transactional
	public List<Custode> tutti() {
		return (List<Custode>) custodeRepository.findAll();
	}

	@Transactional
	public Custode custodePerId(Long id) {
		Optional<Custode> optional = custodeRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Custode custode) {
		List<Custode> custodi = this.custodeRepository.findByNomeAndCognome(custode.getNome(), custode.getCognome());
		if (custodi.size() > 0)
			return true;
		else 
			return false;
	}

	@Transactional
	public void elimina(Custode custode) {
		this.custodeRepository.delete(custode);
	}

	



}
