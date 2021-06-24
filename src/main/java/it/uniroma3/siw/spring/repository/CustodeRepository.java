package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Custode;

public interface CustodeRepository extends CrudRepository<Custode,Long>{
	
		public List<Custode> findByNomeAndCognome(String nome,String cognome);

		public List<Custode> findByNome(String nome);

		public List<Custode> findByCognome(String cognome);

	}


