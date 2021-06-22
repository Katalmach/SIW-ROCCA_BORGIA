package it.uniroma3.siw.spring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.model.User;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione,Long>{

	public List<Prenotazione> findByGiornoAndOra(LocalDate giorno, int ora);

	
	@Query("FROM Prenotazione WHERE campo=?1 ORDER BY giorno, ora ASC")
	public List<Prenotazione> findByCampo(Campo campo);

	@Query("FROM Prenotazione WHERE user=?1 ORDER BY giorno, ora ASC " )
	public List<Prenotazione> findByUser(User user);

	@Override
	@Query("FROM Prenotazione ORDER BY giorno, ora ASC")
	public List<Prenotazione> findAll();
	
	
	public Optional<Prenotazione> findByGiornoAndOraAndCampo(LocalDate giorno, int ora, Campo campo);
}
