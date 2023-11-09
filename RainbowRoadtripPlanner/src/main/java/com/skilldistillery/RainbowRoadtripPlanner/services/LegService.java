package com.skilldistillery.RainbowRoadtripPlanner.services;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Leg;

public interface LegService {

	Leg createLeg(String username, Leg leg, int tripid);

	Leg updateLeg(int legId, Leg leg);

	boolean deleteLeg(int legId);

	Leg getLegById(int tripid, int legId, String username);
	
	

}