package com.mussab.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mussab.restservices.entities.User;
import com.mussab.restservices.exceptions.UserExistsException;
import com.mussab.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		if(userRepository.findByUsername(user.getUsername()) != null) {
			throw new UserExistsException("User already exists in user repository.");
		}
		return userRepository.save(user);
	}
	
	//getUserById method
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in user repository.");
		}
		return user;
	}
	
	//updateUserById method
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException("User not found in user repository. Provide the correct user id.");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	//deleteUserById method
	public void deleteUserById(Long id) {
//		if(userRepository.findById(id).isPresent()) {
		if(!userRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user repository.");
		}
		userRepository.deleteById(id);
	}
	
	//getUserByUsername method
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
