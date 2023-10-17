package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Roles;
import com.demo.domain.User;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repo;

// getting the list
	public List<Roles> listAll() {
		return repo.findAll();
	}

// saving the Data
	public void save(Roles roles) {
		repo.save(roles);
	}

// getting specific Data
	public Roles get(Long id) {
		return repo.findById(id).get();
	}
	
	 

// delete specific Data
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
