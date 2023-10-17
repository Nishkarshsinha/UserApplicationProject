package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Dto.UserDto;
import com.demo.domain.User;
import com.demo.service.UserService;
import com.demo.utility.UrlMappings;

@RestController
public class UserController {

	@Autowired
	private UserService service;


	@GetMapping(UrlMappings.GET_ALL_USER)
	public List<User> list() {
		return service.listAll();
	}


	@GetMapping(UrlMappings.GET_USER_BY_ID)
	public ResponseEntity<User> get(@RequestParam Long id) {
		try {
			User user = service.get(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		}

	}


	@PostMapping(UrlMappings.ADD_NEW_USER)
	public ResponseEntity<?> add(@RequestBody UserDto userDto) {
		try {
			User user=service.saveUser(userDto);
			if(user!=null) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}


	@PutMapping(UrlMappings.UPDATE_USER)
	public ResponseEntity<?> update(@RequestBody UserDto user) {
		try {
			boolean flag= service.updateUser(user);
			if(flag) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
	}


	@DeleteMapping(UrlMappings.DELETE_USER)
	public void delete(@RequestParam Long id) {
		service.delete(id);
		return;
	}
	
	
}
