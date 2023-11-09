package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Trip;
import com.skilldistillery.RainbowRoadtripPlanner.entities.User;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> listAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	@Override
	public User create(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User update(int id, User user) {
		User existing = userRepo.findById(id).get();
		if (existing != null) {
			existing.setUsername(user.getUsername());
			existing.setPassword(user.getPassword());
			existing.setEnabled(user.isEnabled());
			existing.setRole(user.getRole());
			existing.setActive(user.getActive());
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setPhone(user.getPhone());
			existing.setImageUrl(user.getImageUrl());
			existing.setAboutMe(user.getAboutMe());			
			existing.setCreateDate(user.getCreateDate());
			existing.setUpdateDate(user.getUpdateDate());
			userRepo.saveAndFlush(existing);
			return existing;
		}
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		Boolean deleted = false;
		User toDelete = userRepo.findById(id).get();
		if (toDelete != null) {
			userRepo.delete(toDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Optional<User> findById(int id) {
		
		return userRepo.findById(id);
	}



}
