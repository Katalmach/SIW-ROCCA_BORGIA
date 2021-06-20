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

	public List<Prenotazione> findByCampo(Campo campo);

	public List<Prenotazione> findByUser(User user);

	@Query("FROM Prenotazione ORDER BY giorno, ora ASC")
	public Optional<Prenotazione> findByGiornoAndOraAndCampo(LocalDate giorno, int ora, Campo campo);
}
