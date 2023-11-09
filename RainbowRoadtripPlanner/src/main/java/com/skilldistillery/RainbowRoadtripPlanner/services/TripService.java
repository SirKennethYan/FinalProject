package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Trip;

public interface TripService {
	public List<Trip> index();
	public Trip findTripById(int id);
	public Trip create(String username, Trip trip);
	public Trip update(String username, int id, Trip trip);
	public boolean destroy(String username, int id);
	List<Trip> findUserTrips(String username);
	List<Trip> findByUserId(int id);
}
