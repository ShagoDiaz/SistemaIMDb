package com.edutecno.imdb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutecno.imdb.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findAll();

	List<Role> findByName(String name);

}
