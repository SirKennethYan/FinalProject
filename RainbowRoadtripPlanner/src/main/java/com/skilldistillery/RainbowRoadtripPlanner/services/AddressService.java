package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Address;

public interface AddressService {
	
	public List<Address> index();
	public Address create(Address address);
	public Address update(Address address, int id);
	public boolean destroy(int id);
	public List<Address> findByState(String state);

}
