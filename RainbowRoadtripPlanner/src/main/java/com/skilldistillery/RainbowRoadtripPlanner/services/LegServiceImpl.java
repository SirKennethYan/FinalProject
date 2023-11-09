package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Leg;
import com.skilldistillery.RainbowRoadtripPlanner.entities.Trip;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.LegRepository;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.TripRepository;

@Service
public class LegServiceImpl implements LegService {
	
	@Autowired
	private LegRepository legRepo;
	
	@Autowired
	private TripRepository tripRepo;

	@Override
	public Leg createLeg(String username, Leg leg, int tripid) {
		Trip trip = tripRepo.findByIdAndUser_Username(tripid, username);
		leg.setTrip(trip);
		List<Leg> legsOfTrip = trip.getLegs();
		legsOfTrip.add(leg);
		legRepo.saveAndFlush(leg);
		tripRepo.saveAndFlush(trip);
		return leg;
	}

	@Override
	public Leg updateLeg(int legId, Leg leg) {
		Leg existing = legRepo.findById(legId).get();
		if(existing != null) {
			existing.setStartDestination(leg.getStartDestination());
			existing.setNotes(leg.getNotes());
			existing.setName(leg.getName());
			existing.setLegNumber(leg.getLegNumber());
			existing.setEstimatedMiles(leg.getEstimatedMiles());
			existing.setEndDestination(leg.getEndDestination());
			existing.setDescription(leg.getDescription());
			existing.setActualMiles(leg.getActualMiles());
			existing.setActive(leg.getActive());
			System.out.println(existing);
			
			return legRepo.saveAndFlush(existing);
			
		}
		return null;
	}

	@Override
	public boolean deleteLeg(int legId) {
		boolean deleted = false;
		Leg legToDelete = legRepo.findById(legId).get();
		if(legToDelete != null) {
			legRepo.delete(legToDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Leg getLegById(int tripid,int id, String username) {
		return legRepo.findByIdAndTrip_Id(tripid, id);
	}

}