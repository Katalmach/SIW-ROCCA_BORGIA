package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Campo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable=false)
	private String matricola;

	private String image;
	
	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	@Column(length = 2000)
	private String descrizione;

	@Column(nullable=false)
	private boolean disponibilita;

	@Column(nullable=false)
	private float prezzoOrario;

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private Sport sport;

	@OneToMany(mappedBy ="campo")
	private List<PrenotazioneCampo> prenotazioni;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPrezzoOrario() {
		return prezzoOrario;
	}

	public void setPrezzoOrario(float prezzoOrario) {
		this.prezzoOrario = prezzoOrario;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public List<PrenotazioneCampo> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<PrenotazioneCampo> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}

