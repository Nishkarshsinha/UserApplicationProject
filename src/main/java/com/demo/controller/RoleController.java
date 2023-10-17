package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Roles;
import com.demo.domain.User;
import com.demo.service.RoleService;
import com.demo.service.UserService;

@RestController
public class RoleController {

	@Autowired
	private RoleService service;


	@GetMapping("/Roles/getAllRoles")
	public List<Roles> list() {
		return service.listAll();
	}


	@GetMapping("/Roles/getRolesById")
	public ResponseEntity<Roles> get(@RequestParam Long id) {
		try {
			Roles roles = service.get(id);
			return new ResponseEntity<Roles>(roles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Roles>(HttpStatus.NOT_FOUND);

		}

	}


	@PostMapping("/Roles/addNewRoles")
	public void add(@RequestBody Roles roles) {
		service.save(roles);
	}


	@PutMapping("/Roles/updateRole")
	public ResponseEntity<?> update(@RequestBody Roles roles, @RequestParam Long id) {
		try {
			Roles user2 = service.get(id);
			service.save(roles);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
	}


	@DeleteMapping("/Roles/deleteRolesId")
	public void delete(@RequestParam Long id) {
		service.delete(id);
		return;
	}
	
	
}
