package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Accomodation;

public interface AccomodationRepository extends JpaRepository<Accomodation, Integer>{
		
		Accomodation findById(int id);
}
