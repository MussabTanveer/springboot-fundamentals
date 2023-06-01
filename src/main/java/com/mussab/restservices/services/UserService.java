package com.mussab.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mussab.restservices.entities.User;
import com.mussab.restservices.repositories.UserRepository;

// Service
@Service
public class UserService {
	// Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;
	
	//getAllUsers method
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	//createUser Method
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	//getUserById method
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	//updateUserById method
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//deleteUserById method
	public void deleteUserById(Long id) {
//		if(userRepository.findById(id).isPresent()) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
	}
	
	//getUserByUsername method
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
