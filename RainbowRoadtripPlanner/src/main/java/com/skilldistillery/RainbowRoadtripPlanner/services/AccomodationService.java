package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Accomodation;

public interface AccomodationService {
	
	public List<Accomodation> index();
	public Accomodation create(Accomodation acccomodation);
	public Accomodation update(Accomodation acccomodation, int id);
	

}
