package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Destination;

public interface DestinationService {

		public List<Destination> index();
		public List<Destination> findByType(String type);
		public Destination findByAddressId(int startId);
		public Destination create(Destination destination);
		public Destination update(Destination dest, int id);
		public boolean destroy(int id);
		public Destination findById(int id);
	
}
