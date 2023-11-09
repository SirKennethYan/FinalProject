package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	List<Vehicle> findByUser_Username(String username);
	Vehicle findById(int id);
	Vehicle findByIdAndUser_Username(int id, String username);
	Vehicle deleteById(int id);
	List<Vehicle> findByUser_Id(int id);
}
