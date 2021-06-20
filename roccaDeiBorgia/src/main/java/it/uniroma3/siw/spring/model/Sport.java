package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sport {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(nullable = false)
	private int numGiocatoriMax;

	@OneToMany(mappedBy="sport")
	private List<Campo> campi;



	public List<Campo> getCampi() {
		return campi;
	}

	public void setCampi(List<Campo> campi) {
		this.campi = campi;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumGiocatoriMax() {
		return numGiocatoriMax;
	}

	public void setNumGiocatoriMax(int numGiocatoriMax) {
		this.numGiocatoriMax = numGiocatoriMax;
	}

}
