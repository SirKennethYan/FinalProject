package com.skilldistillery.RainbowRoadtripPlanner.controllers;

import java.security.Principal;

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

import com.skilldistillery.RainbowRoadtripPlanner.entities.Leg;
import com.skilldistillery.RainbowRoadtripPlanner.services.LegService;
import com.skilldistillery.RainbowRoadtripPlanner.services.TripService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class LegController {
	
	@Autowired
	private LegService legServ;
	
	
	@PostMapping("legs")
	public Leg createLeg(Principal principal, @RequestBody Leg leg, HttpServletResponse res) {
		int tripid = 1;
		legServ.createLeg(principal.getName(), leg, tripid);
		return leg;
	}
	
	@PutMapping("trips/{tripid}/legs/{id}")
	public Leg updateLeg(@RequestBody Leg leg, @PathVariable Integer id, HttpServletResponse res) {
		return legServ.updateLeg(id, leg);
	}
	
	@DeleteMapping("trips/{tripid}/legs/{id}")
	public boolean deleteLeg(@PathVariable int legId, HttpServletResponse res) {
		try {
			if (legServ.deleteLeg(legId)) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return true;

	}
	
//	@GetMapping("trips/{tripid}/legs/{id}")
//	public Leg findById(Principal principal, @PathVariable int id, @PathVariable int tripid) {
//		return legServ.getLegById(tripid, id);
//	}

}