package com.edutecno.imdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutecno.imdb.model.User;

@Service
public interface UserService {

	public void saveWithUserRole(User user);

	public void saveWithAdminRole(User user);

	public User findByUserName(String username);

	public User findById(Long id);

	public User findByEmail(String email);

	public List<User> findAllUser();

	public void create(User user);

	public void update(User user);

	public void delete(Long id);
}
