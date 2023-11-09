package com.skilldistillery.RainbowRoadtripPlanner.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Trip;
import com.skilldistillery.RainbowRoadtripPlanner.services.TripService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class TripController {
	
	@Autowired
	private TripService tripService;
	
	@GetMapping("trips")
	public List<Trip> index( HttpServletRequest req, HttpServletResponse res) {
		return tripService.index();
	}
	
	@GetMapping("trips/{id}")
	public Trip getSingleTripById(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		return tripService.findTripById(id);
	}
	
	@GetMapping("users/trips")
	public List<Trip> getUserTrips(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		return tripService.findUserTrips(principal.getName());
	}
	
	@GetMapping("users/trips/id/{id}")
	public List<Trip> getUserTripsById(HttpServletRequest req, HttpServletResponse res, @PathVariable int id){
		return tripService.findByUserId(id);
	}
	
	@PostMapping("trips")
	public Trip create(Principal principal, HttpServletRequest req, HttpServletResponse res, @RequestBody Trip trip) {
		Trip createTrip = null;
		try {
			createTrip = tripService.create(principal.getName(), trip);
			if (trip != null) {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				res.setHeader("Location", url.append("/").append(trip.getId()).toString());
			} else {
				res.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			trip = null;
		}
		return createTrip;
	}
	
	@PutMapping("trips/{id}")
	public Trip update(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id, @RequestBody Trip trip) {
		Trip updatedTodo = null;
		try {
			updatedTodo = tripService.update(principal.getName(), id, trip);
			if (updatedTodo == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updatedTodo;
	}
	
	@DeleteMapping("trips/{id}")
	public void destroy(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		boolean deleted = false;
		try {
			deleted = tripService.destroy(principal.getName(), id);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
