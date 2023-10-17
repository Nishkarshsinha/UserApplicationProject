package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{

	List<Roles> findAllByIdIn(List<Long> id);

}
