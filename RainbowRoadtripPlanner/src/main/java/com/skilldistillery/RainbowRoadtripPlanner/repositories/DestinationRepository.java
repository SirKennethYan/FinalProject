package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer>{
	
		Destination findByAddress_Id(int id);
		Destination findById(int id);
		List<Destination> findByDescriptionLike(String desc);
		
}
