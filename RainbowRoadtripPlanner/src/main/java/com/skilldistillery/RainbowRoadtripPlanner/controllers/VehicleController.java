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

import com.skilldistillery.RainbowRoadtripPlanner.entities.Vehicle;
import com.skilldistillery.RainbowRoadtripPlanner.services.VehicleService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	
	@GetMapping("vehicles")
public List<Vehicle> showAll(Principal principal,HttpServletRequest req, HttpServletResponse res) {
		return vehicleService.showAll(principal.getName());
	}
	
	
	@GetMapping("vehicles/{id}")
	public Vehicle getVehicleById(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		Vehicle findVehicles = vehicleService.show(principal.getName(), id);
		if (findVehicles == null) {
			res.setStatus(404);
		}
		return findVehicles;

	}

	@PostMapping("vehicles")
	public Vehicle createVehicle(Principal principal, @RequestBody Vehicle vehicle, HttpServletResponse res) {
		Vehicle addVehicle = null;
		try {
			addVehicle = vehicleService.create(principal.getName(), vehicle);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return addVehicle;

	}

	@PutMapping("vehicles/{id}")
	public Vehicle update(Principal principal, HttpServletRequest req, @RequestBody Vehicle vehicle,
			@PathVariable int id, HttpServletResponse res) {
		Vehicle updated = null;
		try {
			updated = vehicleService.update(principal.getName(), id, vehicle);
			if (updated != null) {
				res.setStatus(200);
			} else {
				res.setStatus(400);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}

	@DeleteMapping("vehicles/{id}")
	public boolean destroy(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		boolean deleted = false;

		try {
			deleted = vehicleService.destroy(principal.getName(), id);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(500);
		}
		return deleted;
	}
	
	
	@GetMapping("vehicles/user/{id}")
	public List<Vehicle> findUsersVehicles(HttpServletRequest req, HttpServletResponse res, @PathVariable int id){
			return vehicleService.findUsersVehicles(id);
		
		
	}
	

}


