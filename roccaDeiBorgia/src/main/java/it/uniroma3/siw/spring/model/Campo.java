package it.uniroma3.siw.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Campo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable=false)
	private String matricola;

	private String image;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private Sport sport;

	@Column(length = 2000)
	private String descrizione;

	
	@Column(nullable=false)
	private float prezzoOrario;

	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	private Custode custode;
	
	//getters & setters

	public String getMatricola() {
		return matricola;
	}

	public Custode getCustode() {
		return custode;
	}

	public void setCustode(Custode custode) {
		this.custode = custode;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}

