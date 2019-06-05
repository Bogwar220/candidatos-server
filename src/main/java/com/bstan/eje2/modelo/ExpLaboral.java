package com.bstan.eje2.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name ="experiencia_laboral")
public class ExpLaboral {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date fechaIni;
	private Date fechaFin;
	private String empresa;
	private String cliente;
	private String puesto;
	private String funciones;
	
	@ManyToOne
	private Candidato candidato;
	
	public ExpLaboral() {
		
	}

	public long getId() {
		return id;
	}

	public ExpLaboral(Date fechaIni, Date fechaFin, String empresa, String cliente, String puesto, String funciones,
			Candidato candidato) {
		super();
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.empresa = empresa;
		this.cliente = cliente;
		this.puesto = puesto;
		this.funciones = funciones;
		this.candidato = candidato;
	}
	public Candidato getCandidato() {
		return candidato;
	}
	
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	
}
