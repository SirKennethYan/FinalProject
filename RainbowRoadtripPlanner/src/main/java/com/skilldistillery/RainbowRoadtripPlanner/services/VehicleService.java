package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Vehicle;

public interface VehicleService {
	
	public List<Vehicle> showAll(String username);
	
	public Vehicle show(String username, int id);
	
	public Vehicle create(String username, Vehicle vehicle);
	
	public Vehicle update(String username, int id, Vehicle vehicle);
	
	public boolean destroy(String username, int id);
	
	public List<Vehicle> findUsersVehicles(int id);


}
