package com.skilldistillery.RainbowRoadtripPlanner.services;

import com.skilldistillery.RainbowRoadtripPlanner.entities.User;

public interface AuthService{

	public User register(User user);
	public User getUserByUsername(String username);
	
}
