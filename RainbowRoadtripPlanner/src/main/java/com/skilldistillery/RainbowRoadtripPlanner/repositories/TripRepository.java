package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer>{
	Trip findById(int id);
	Trip findByIdAndUser_Username(int id, String username);
	Trip deleteById(int id);
	List<Trip> findByUser_Username(String username);
	List<Trip> findByUserId(int id);
}
