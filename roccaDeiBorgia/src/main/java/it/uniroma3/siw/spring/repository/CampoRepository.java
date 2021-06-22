package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Sport;

public interface CampoRepository extends CrudRepository<Campo, Long>{
	
	public List<Campo> findByMatricola(String matricola);
	
	public List<Campo> findBySport(Sport sport);

	public List<Campo> findByPrezzoOrario(float prezzoOrario );

	public List<Campo> findBySportAndPrezzoOrario(Sport sport, float prezzoOrario);
	
}