package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer>{
	
		Activity findByPriorityLevel(int lvl);
		List<Activity> findByDestinations(int id);
		Activity findById(int id);
	
	
}
