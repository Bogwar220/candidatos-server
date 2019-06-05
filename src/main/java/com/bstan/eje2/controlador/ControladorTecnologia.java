package com.bstan.eje2.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bstan.eje2.modelo.CandTec;
import com.bstan.eje2.modelo.Tecnologia;
import com.bstan.eje2.repositories.RepTecnologia;
import com.bstan.eje2.repositories.RepCandTec;

@RestController
public class ControladorTecnologia {

	@Autowired
	private RepTecnologia repTec;
	@Autowired
	private RepCandTec repCandTec;
	
	@CrossOrigin
	@PostMapping (path = "/tecnologia")
	public @ResponseBody Tecnologia addTec(@RequestBody Tecnologia newTec) {
		repTec.save(newTec);
		return newTec;
	}
	
	@CrossOrigin
	@GetMapping (path = "/tecnologia")
	public @ResponseBody Object getTec() {
		return repTec.findAll();
	}
//	public @ResponseBody Tecnologia getTec(@RequestBody Tecnologia unTec) {
//		Iterable<Tecnologia> iterTec = repTec.findAll();
//		for(Tecnologia tec : iterTec) {
//			if(tec.getNombre().equals(unTec.getNombre()))
//				return tec;
//		}
//		return null;
//	}
	
	@CrossOrigin
	@DeleteMapping (path = "/tecnologia")
	public @ResponseBody Tecnologia delTec(@RequestBody Tecnologia tec) {		
		Iterable<CandTec> iterCandTec = repCandTec.findAll();
		for(CandTec candTec : iterCandTec) {
			if(candTec.getTecnologia().getId() == tec.getId())
				repCandTec.delete(candTec);
		}
		Iterable<Tecnologia> iterTec = repTec.findAll();
		for(Tecnologia tecnologia : iterTec) {
			if(tecnologia.getId() == tec.getId()) 
				repTec.delete(tecnologia);
		}
		return tec;
	}
	
	@CrossOrigin
	@PutMapping(path = "/tecnologia")
	public @ResponseBody Tecnologia modifTec(@RequestBody Tecnologia tec) {
		if(tec.getId() == 0)
			return null;
		Optional<Tecnologia> opTec = repTec.findById(tec.getId());
		Tecnologia newTec = opTec.get();
		
		if(tec.getNombre() != null)
			newTec.setNombre(tec.getNombre());
		if(tec.getDescripcion() != null)
			newTec.setDescripcion(tec.getDescripcion());
		
		repTec.save(newTec);
		return newTec;
	}
}
