package com.bstan.eje2.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bstan.eje2.modelo.User;
import com.bstan.eje2.repositories.RepUser;

@RestController
public class ControladorUser {

	@Autowired
	private RepUser repUser;
	
	@CrossOrigin
	@PostMapping(path = "/user")
	public @ResponseBody Object addUser(@RequestBody User user) {
		repUser.save(user);
		return user;
	}
	
	@CrossOrigin
	@GetMapping(path = "/user")
	public @ResponseBody Object getUser(@RequestParam String nombre,
			@RequestParam String pass) {
		Iterable<User> iterUser = repUser.findAll();
		for(User user: iterUser) {
			if(user.getNombre().equals(nombre) && user.getPass().equals(pass))
				return user;
		}
		return null;
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/user")
	public @ResponseBody User delUser(@RequestBody User user) {
		Iterable<User> iterUser = repUser.findAll();
		for(User buscador : iterUser) {
			if(buscador.getId() == user.getId())
				repUser.delete(buscador);
		}
		
		return user;
	}
	
	@CrossOrigin
	@PutMapping(path = "/user")
	public @ResponseBody User modifUser(@RequestBody User user) {
		if(user.getId() == 0)
			return null;
		
		Optional<User> opUser = repUser.findById(user.getId());
		User newUser = opUser.get();
		
		if(user.getPass() != null)
			newUser.setNombre(user.getNombre());
		
		repUser.save(newUser);
		return newUser;
	}
}
