package com.bstan.eje2.controlador;

import java.util.Optional;

import org.apache.tomcat.jni.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bstan.eje2.modelo.CandTec;
import com.bstan.eje2.modelo.Candidato;
import com.bstan.eje2.modelo.ExpLaboral;
import com.bstan.eje2.modelo.Titulo;
import com.bstan.eje2.repositories.RepCandTec;
import com.bstan.eje2.repositories.RepCandidato;
import com.bstan.eje2.repositories.RepExpLaboral;
import com.bstan.eje2.repositories.RepTitulo;

@RestController
public class ControladorCandidato {

	@Autowired
	private RepCandidato repCand;
	@Autowired
	private RepTitulo repTit;
	@Autowired
	private RepExpLaboral repExpLab;
	@Autowired
	private RepCandTec repCandTec;
	
	@CrossOrigin
	@PostMapping(path = "/candidato")
	public @ResponseBody Object addCand(@RequestBody Candidato newCand) {	
		repCand.save(newCand);
		return newCand;
	}
	
	@CrossOrigin
	@GetMapping(path = "/candidato")
	public @ResponseBody Object getCand() {
		return repCand.findAll();
	}
	
//	@GetMapping(path = "/candidato")
//	public @ResponseBody Candidato getCand (@RequestBody Candidato unCand) {
//		Iterable<Candidato> iterCand = repCand.findAll();
//		for(Candidato cand : iterCand) {
//			if(cand.getNombre().equals(unCand.getNombre()))
//				return cand;
//		}
//		return null;
//	}
	
	@CrossOrigin
	@DeleteMapping(path = "/candidato")
	public @ResponseBody Candidato delCand(@RequestBody Candidato cand) {
		
		Iterable<Titulo> iterTit = repTit.findAll();
		for (Titulo titulo : iterTit) {
			if(cand.getId() == titulo.getCandidato().getId())
				repTit.delete(titulo);
		}
		
		Iterable<ExpLaboral> iterExpLab = repExpLab.findAll();
		for(ExpLaboral expLab : iterExpLab) {
			if(cand.getId() == expLab.getCandidato().getId())
				repExpLab.delete(expLab);
		}
		
		Iterable<CandTec> iterCandTec = repCandTec.findAll();
		for(CandTec candTec : iterCandTec) {
			if(candTec.getCandidato().getId() == cand.getId())
				repCandTec.delete(candTec);
		}
		
		Iterable<Candidato> iterCand = repCand.findAll();
		for(Candidato buscador : iterCand) {
			if(buscador.getId() == cand.getId())
				repCand.delete(buscador);
		}
		
		return cand;
	}	
	
	@CrossOrigin
	@PutMapping (path = "/candidato")
	public @ResponseBody Candidato modifCand(@RequestBody Candidato cand) {
		if(cand.getId() == 0)
			return null;
		
		Optional<Candidato> opCand = repCand.findById(cand.getId());
		
		Candidato newCand = opCand.get();
		
		if(cand.getNombre() != null)
			newCand.setNombre(cand.getNombre());
		if(cand.getApellido1() != null)
			newCand.setApellido1(cand.getApellido1());
		if(cand.getApellido2() != null)
			newCand.setApellido2(cand.getApellido2());
		if(cand.getTlf() != null)
			newCand.setTlf(cand.getTlf());
		if(cand.getEmail() != null)
			newCand.setEmail(cand.getEmail());
		if(cand.getPoblacion() != null)
			newCand.setPoblacion(cand.getPoblacion());
		if(cand.getCp() != 0)
			newCand.setCp((int) cand.getCp());
		if(cand.getDireccion() != null)
			newCand.setDireccion(cand.getDireccion());
		if(cand.getDni() != null)
			newCand.setDni(cand.getDni());
		if(cand.getFechaNac() != null)
			newCand.setFechaNac(cand.getFechaNac());
		if(cand.getFechaAlta() != null)
			newCand.setFechaAlta(cand.getFechaAlta());
		if(cand.getFechaBaja() != null)
			newCand.setFechaBaja(cand.getFechaBaja());
		
		repCand.save(newCand);
		return newCand;
	}
}
