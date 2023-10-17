package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long>{

	Salary findBySalaryAmount(Long salary);

}
