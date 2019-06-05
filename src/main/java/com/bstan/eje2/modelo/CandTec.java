package com.bstan.eje2.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "candidato_tecnologia")
public class CandTec {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Candidato candidato;
	
	@ManyToOne
	private Tecnologia tecnologia;
	
	private Integer nivel;
	
	public CandTec() {
		
	}

	public CandTec(Candidato candidato, Tecnologia tecnologia, Integer nivel) {
		super();
		this.candidato = candidato;
		this.tecnologia = tecnologia;		
		this.nivel = nivel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	
}
