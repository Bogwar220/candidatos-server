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
import org.springframework.web.bind.annotation.RestController;

import com.bstan.eje2.modelo.CandTec;
import com.bstan.eje2.modelo.Candidato;
import com.bstan.eje2.modelo.Tecnologia;
import com.bstan.eje2.repositories.RepCandTec;
import com.bstan.eje2.repositories.RepCandidato;
import com.bstan.eje2.repositories.RepTecnologia;

@RestController

public class ControladorCandTec {

	@Autowired
	private RepCandTec repCandTec;
	@Autowired
	private RepCandidato repCand;
	@Autowired
	private RepTecnologia repTec;
	
	@CrossOrigin
	@PostMapping (path = "/candTec")
	public @ResponseBody CandTec addCandtec(@RequestBody CandTec newCandTec) {
		Iterable<Candidato> iterCand = repCand.findAll();
		for(Candidato cand : iterCand) {
			if(cand.getId() == newCandTec.getCandidato().getId()) {
				newCandTec.setCandidato(cand);
			}
		}
		
		Iterable<Tecnologia> iterTec = repTec.findAll();
		for(Tecnologia tec: iterTec) {
			if(tec.getId() == newCandTec.getTecnologia().getId()) {
				newCandTec.setTecnologia(tec);
			}
		}
		
		Iterable<CandTec> iterCandTec = repCandTec.findAll();
		for(CandTec candTec : iterCandTec) {
			if((candTec.getCandidato().getId() == newCandTec.getCandidato().getId()) && 
					(candTec.getTecnologia().getId() == newCandTec.getTecnologia().getId())) {
				return null;
			}
		}
		
		repCandTec.save(newCandTec);
		return newCandTec;
	}
	
//	@CrossOrigin
//	@GetMapping(path = "/candTecSi")
//	public @ResponseBody Object getCandTecSi(@RequestParam int candId) {
//		if(candId == 0) {
//			return repTec.findAll();
//		}else {
//			List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
//			Iterable<CandTec> iterCandTec = repCandTec.findAll();
//			for (CandTec candTec: iterCandTec) {
//				if(candTec.getCandidato().getId() == candId) {
//					tecnologias.add(candTec.getTecnologia());
//				}
//			}
//			return tecnologias;
//		}		
//	}
	
	@CrossOrigin
	@GetMapping(path = "/candTecSi")
	public @ResponseBody Object getCandTecSi(@RequestParam int candId) {
		List<CandTec> candTecs = new ArrayList<CandTec>();
		Iterable<CandTec> iterCandTec = repCandTec.findAll();
		for(CandTec candTec: iterCandTec) {
			if(candTec.getCandidato().getId() == candId) {
				if(candTec.getNivel() == null) 
					candTec.setNivel(0);
				candTecs.add(candTec);
			}
		}
		return candTecs;
	}
	@CrossOrigin
	@GetMapping(path = "/candTecNo")	
	public @ResponseBody Object getCandTecNo(@RequestParam int candId) {
		if(candId == 0) {
			return null;
		}else {
			List<Tecnologia> tecAll = (List<Tecnologia>) repTec.findAll();
			Iterable<CandTec> iterCandTec = repCandTec.findAll();
			for(CandTec candTec : iterCandTec) {
				if(candTec.getCandidato().getId() == candId) {
					tecAll.remove(candTec.getTecnologia());
				}
			}
			return tecAll;
		}
	}
	
//	@CrossOrigin
//	@GetMapping(path ="/candTec")
//	public @ResponseBody Object getCandTec(@RequestParam int candId, @RequestParam int tecId) {
//		Iterable<CandTec> iterCandTec = repCandTec.findAll();
//		for(CandTec candTec : iterCandTec) {
//			if(candTec.getCandidato().getId() == candId && candTec.getTecnologia().getId() == tecId)
//				return candTec;
//		}
//		return null;
//	}
//	public @ResponseBody Object getCandTec (@RequestBody CandTec candTecs) {		
//		List<Tecnologia> tecnologias= new ArrayList<Tecnologia>();
//		Iterable<CandTec> iterCandTec = repCandTec.findAll();
//		System.out.println("entra");
//		for(CandTec candTec : iterCandTec) {			
//			if(candTec.getCandidato().getId() == candTecs.getCandidato().getId()) {
//				tecnologias.add(candTec.getTecnologia());
//				System.out.println(candTec.getCandidato().getId());		
//			}
//		}		
//		return tecnologias;
//	}
	
//	@CrossOrigin
//	@GetMapping(path = "/candTecs")
//	public @ResponseBody Object getCandTecs(@RequestBody CandTec candTec) {
//		System.out.println(candTec.getCandidato().getId());
//		return repCandTec.findAll();
//	}
	
//	public @ResponseBody CandTec getCandTec (@RequestBody CandTec unCandTec) {
//		Iterable<CandTec> iterCandTec = repCandTec.findAll();
//		for(CandTec candTec : iterCandTec) {
//			if(candTec.getId() == unCandTec.getId())
//				return candTec;
//		}
//		return null;
//	}
	
	@CrossOrigin
	@DeleteMapping(path = "/candTec")
	public @ResponseBody Object delCandTec(@RequestBody CandTec candTec) {
		Iterable<CandTec> iterCandTec = repCandTec.findAll();
		for(CandTec someCandTec : iterCandTec) {
			if(someCandTec.getId() == candTec.getId()) {
				repCandTec.delete(someCandTec);
				return candTec;
			}				
		}
		return candTec;
		
	}

	
//	@CrossOrigin
//	@DeleteMapping (path = "/candTec")
//	public @ResponseBody CandTec delCandTec(@RequestBody CandTec candTec) {
//		Iterable<CandTec> iterCandTec = repCandTec.findAll();
//		for(CandTec unCandTec : iterCandTec) {
//			if(candTec.getCandidato().getId() == unCandTec.getCandidato().getId() && candTec.getTecnologia().getId() == unCandTec.getTecnologia().getId()) {
//				repCandTec.delete(unCandTec);
//				return unCandTec;
//			}
//		}
//		return candTec;
//	}
	
	@CrossOrigin
	@PutMapping (path = "/candTec")
	public @ResponseBody CandTec modifCandtec(@RequestBody CandTec candTec) {
		if(candTec.getId() == 0)
			return null;
		Optional<CandTec> opCandTec = repCandTec.findById(candTec.getId());
		CandTec newCandTec = opCandTec.get();
		
		if(candTec.getCandidato() != null)
			newCandTec.setCandidato(candTec.getCandidato());
		if(candTec.getTecnologia() != null)
			newCandTec.setTecnologia(candTec.getTecnologia());		
		if(candTec.getNivel() != 0)
			newCandTec.setNivel(candTec.getNivel());
		
		repCandTec.save(newCandTec);
		return newCandTec;
	}
}
