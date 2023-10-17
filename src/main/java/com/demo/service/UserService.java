package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.demo.Dto.UserDto;
import com.demo.domain.Roles;
import com.demo.domain.Salary;
import com.demo.domain.User;
import com.demo.repository.RoleRepository;
import com.demo.repository.SalaryRepository;
import com.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private RoleRepository rolesRepo;
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	MailService mailService;
	
	

// getting the list
	public List<User> listAll() {
		return repo.findAll();
	}

// saving the Data
	public User saveUser(UserDto userDto) throws Exception {
		User user =new User();
		user.setFullName(userDto.getFullName());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		List<Roles>roles=rolesRepo.findAllByIdIn(userDto.getRole());
		List<User>userList=repo.findAllByIdIn(userDto.getTeams());
		Salary salary=salaryRepository.findBySalaryAmount(userDto.getSalary());
		if(!roles.isEmpty()) {
			user.setRole(roles);
		}
		if(!userList.isEmpty()) {
			user.setTeams(userList);
		}
		if(salary!=null) {
			
			user.setSalary(salary);
		}else {
			Salary newSalary=new Salary();
			newSalary.setSalaryAmount(userDto.getSalary());
			salaryRepository.save(newSalary);
			user.setSalary(newSalary);
		}
		User res= repo.save(user);
		sendMail(res);
		return res;
	}
	
	private void sendMail(User user)throws Exception {
		
			Context context = new Context();
//			Date date=new Date(potentialLead.getSavedOn().getTime()+19800000);
//			String singleUrl = link + "/client/post/"+potentialLead.getUniqueId();
			String subject= "Welcome to aboard";
			String name=user.getFullName();
			context.setVariable("name", user.getFullName());
			
			try {
				mailService.sendEmailToClient(user.getEmail(),subject, context,"newUserMail");
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
		
	}

// update the Data
		public boolean updateUser(UserDto userDto) {
			User user =repo.findById(userDto.getId()).get();
			boolean flag=false;
			
			if(user!=null) {
				user.setFullName(userDto.getFullName());
				user.setFirstName(userDto.getFirstName());
				user.setLastName(userDto.getLastName());
				user.setEmail(userDto.getEmail());
				List<Roles>roles=rolesRepo.findAllByIdIn(userDto.getRole());
				List<User>userList=repo.findAllByIdIn(userDto.getTeams());
				Salary salary=salaryRepository.findBySalaryAmount(userDto.getSalary());
				if(!roles.isEmpty()) {
					user.setRole(roles);
				}
				if(!userList.isEmpty()) {
					user.setTeams(userList);
				}
				if(salary!=null) {
					
					user.setSalary(salary);
				}else {
					Salary newSalary=new Salary();
					newSalary.setSalaryAmount(userDto.getSalary());
					salaryRepository.save(newSalary);
					user.setSalary(newSalary);
				}
				flag=true;
				repo.save(user);
			}
			return flag;
		}
// getting specific Data
	public User get(Long id) {
		return repo.findById(id).get();
	}
	
	 

// delete specific Data
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
