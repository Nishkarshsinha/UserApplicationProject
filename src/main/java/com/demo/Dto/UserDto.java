package com.demo.Dto;

import java.util.List;

import com.demo.domain.Roles;
import com.demo.domain.Salary;
import com.demo.domain.User;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	
	private String fullName;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private List<Long> role;
	
	private List<Long> teams;
	
	private Long salary;
	
	
}
