package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Sport;

public interface SportRepository extends CrudRepository<Sport, Long> {
	
	public List<Sport> findByid(Long id);
	
	public List<Sport> findByNome(String nome);
	
}
