package it.uniroma3.siw.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.PrenotazioneCampo;
import it.uniroma3.siw.spring.model.User;

public interface PrenotazioneRepository extends CrudRepository<PrenotazioneCampo,Long>{

	public List<PrenotazioneCampo> findByDataInizio(LocalDateTime dataInizio);

	public List<PrenotazioneCampo> findByCampoAndDataInizio(Campo campo, LocalDateTime dataInizio);

	public List<PrenotazioneCampo> findByCampo(Campo campo);

	public List<PrenotazioneCampo> findByUser(User user);

}
