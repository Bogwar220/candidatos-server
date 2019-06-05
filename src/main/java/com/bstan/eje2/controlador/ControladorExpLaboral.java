package com.bstan.eje2.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bstan.eje2.modelo.Candidato;
import com.bstan.eje2.modelo.ExpLaboral;
import com.bstan.eje2.repositories.RepCandidato;
import com.bstan.eje2.repositories.RepExpLaboral;

@Controller
public class ControladorExpLaboral {

	@Autowired
	private RepExpLaboral repExpLab;
	@Autowired
	private RepCandidato repCand;
	
	@CrossOrigin
	@PostMapping (path = "/expLab")
	public @ResponseBody Object addExpLab(@RequestBody ExpLaboral expLab) {
		Optional<Candidato> opCand = repCand.findById(expLab.getCandidato().getId());
		Candidato cand = opCand.get();
		expLab.setCandidato(cand);
		repExpLab.save(expLab);
		return expLab;
	}
	
	@CrossOrigin
	@GetMapping(path = "/expLab")
	public @ResponseBody Object getExpLab(@RequestParam int candId) {
		List<ExpLaboral> expsLab = new ArrayList();
		Iterable<ExpLaboral> iterExpLab = repExpLab.findAll();
		for(ExpLaboral expLab : iterExpLab) {
			if(expLab.getCandidato().getId() == candId)
				expsLab.add(expLab);
		}
		return expsLab;
	}
//	@CrossOrigin
//	@GetMapping(path = "/expLab")
//	public @ResponseBody ExpLaboral getExpLab(@RequestBody ExpLaboral unExpLab) {
//		Iterable<ExpLaboral> iterExpLab = repExpLab.findAll();
//		for(ExpLaboral expLab : iterExpLab) {
//			if(expLab.getId() == unExpLab.getId())
//				return expLab;
//		}
//		return null;
//	}
	
	@CrossOrigin
	@DeleteMapping (path = "/expLab")
	public @ResponseBody Object delExpLab(@RequestBody ExpLaboral expLab) {
		repExpLab.deleteById(expLab.getId());
		return expLab;
	}
	
	@CrossOrigin
	@PutMapping (path = "/expLab")
	public @ResponseBody Object modifExpLab(@RequestBody ExpLaboral expLab) {
		if (expLab.getId() == 0) {
			return null;
		}
		
		Optional<ExpLaboral> opExpLab = repExpLab.findById(expLab.getId());
		ExpLaboral newExpLab = opExpLab.get();
		
		if(expLab.getFechaIni() != null)
			newExpLab.setFechaIni(expLab.getFechaIni());
		if(expLab.getFechaFin() != null)
			newExpLab.setFechaFin(expLab.getFechaFin());
		if(expLab.getEmpresa() != null)
			newExpLab.setEmpresa(expLab.getEmpresa());
		if(expLab.getCliente() != null)
			newExpLab.setCliente(expLab.getCliente());
		if(expLab.getPuesto() != null)
			newExpLab.setPuesto(expLab.getPuesto());
		if(expLab.getFunciones() != null)
			newExpLab.setFunciones(expLab.getFunciones());
		
		repExpLab.save(newExpLab);
		return newExpLab;
	}
}
