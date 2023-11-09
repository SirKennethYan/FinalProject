package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	Address findById(int id);
	List<Address> findByState(String state);
	
}
