package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Sport;
import it.uniroma3.siw.spring.repository.SportRepository;

@Service
public class SportService {
	
	@Autowired
	private SportRepository sportRepository; 
	
	@Transactional
	public Sport inserisci(Sport sport) {
		return sportRepository.save(sport);
	}

	@Transactional
	public List<Sport> tutti() {
		return (List<Sport>) sportRepository.findAll();
	}

	@Transactional
	public Sport sportPerId(Long id) {
		Optional<Sport> optional = sportRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Sport sport) {
		List<Sport> sports = this.sportRepository.findByNome(sport.getNome());
		if (sports.size() > 0)
			return true;
		else 
			return false;
	}
}
