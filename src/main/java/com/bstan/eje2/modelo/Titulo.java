package com.bstan.eje2.modelo;

import java.io.File;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "titulos")
public class Titulo {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String centro;
	private Date fechaExp;
	private String nivelEd;
	private File img;
	
	@ManyToOne
	private Candidato candidato;
	
	public Titulo() {
		
	}

	public Titulo(String nombre, String centro, Date fechaExp, String nivelEd, File img, Candidato candidato) {
		super();
		this.nombre = nombre;
		this.centro = centro;
		this.fechaExp = fechaExp;
		this.nivelEd = nivelEd;
		this.img = img;
		this.candidato = candidato;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public Date getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(Date fechaExp) {
		this.fechaExp = fechaExp;
	}

	public String getNivelEd() {
		return nivelEd;
	}

	public void setNivelEd(String nivelEd) {
		this.nivelEd = nivelEd;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}	
}
