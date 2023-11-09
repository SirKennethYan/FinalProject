package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Leg;

public interface LegRepository extends JpaRepository<Leg, Integer> {

	Leg saveAndFlush(Optional<Leg> leg);
	
	Optional<Leg> findById(Integer id);
	
	Leg findByIdAndTrip_Id(int tripid, int id);

}