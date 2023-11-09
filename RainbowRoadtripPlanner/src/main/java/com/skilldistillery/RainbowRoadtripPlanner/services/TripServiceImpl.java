package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Trip;
import com.skilldistillery.RainbowRoadtripPlanner.entities.User;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.TripRepository;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.UserRepository;
@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripRepository tripRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Trip> index() {
		return tripRepo.findAll();
	}

	@Override
	public Trip create(String username, Trip trip) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			System.out.println(trip);
			System.out.println(trip.getStartDestination());
			System.out.println(trip.getStartDestination().getAddress());
			return tripRepo.saveAndFlush(trip);
		}
		return null;
	}

	@Override
	public Trip update(String username, int id, Trip trip) {
		Trip existing = tripRepo.findByIdAndUser_Username(id, username);
		if (existing != null) {
			existing.setStartDate(trip.getStartDate());
			existing.setEndDate(trip.getEndDate());
			existing.setRoundTrip(trip.isRoundTrip());
			existing.setMiles(trip.getMiles());
			existing.setCreateDate(trip.getCreateDate());
			existing.setUpdateDate(trip.getUpdateDate());
			existing.setTitle(trip.getTitle());
			existing.setDescription(trip.getDescription());
			existing.setImageUrl(trip.getImageUrl());
			tripRepo.saveAndFlush(existing);
			return existing;
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		Trip toDelete = tripRepo.findByIdAndUser_Username(id, username);
		tripRepo.delete(toDelete);
		if (!tripRepo.existsById(id)) {
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<Trip> findUserTrips(String username) {
		// TODO Auto-generated method stub
		return tripRepo.findByUser_Username(username);
	}

	@Override
	public Trip findTripById(int id) {
		return tripRepo.findById(id);
	}

	@Override
	public List<Trip> findByUserId(int id) {
		// TODO Auto-generated method stub
		return tripRepo.findByUserId(id);
	}

}
