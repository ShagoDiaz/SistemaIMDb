package com.edutecno.imdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edutecno.imdb.dao.RoleRepository;
import com.edutecno.imdb.dao.UserRepository;
import com.edutecno.imdb.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_USER"));
		userRepository.save(user);
	}

	public void saveWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
		userRepository.save(user);
	}

	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			User nullUser = new User();
			nullUser.setUsername("User not found");
			return nullUser;
		}
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	public void create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void update(User user) {
		userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
