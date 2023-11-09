package com.skilldistillery.RainbowRoadtripPlanner.controllers;

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

import com.skilldistillery.RainbowRoadtripPlanner.entities.Destination;
import com.skilldistillery.RainbowRoadtripPlanner.services.DestinationService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class DestinationController {
	
	@Autowired
	private DestinationService destSrvc;
	
	@GetMapping("destinations")
	public List<Destination> index(){
		return destSrvc.index();
	}
	
	@GetMapping("destinations/{id}")
	public Destination findById(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		try {
			Destination destination = destSrvc.findById(id);
				res.setStatus(201);
				res.setHeader("Location", "http://localhost:8090/api/destinations" +destination.getId());
				return destination;
			}catch(Exception e){
				e.printStackTrace();
				res.setStatus(400);
			}
			return null;
		}
	
	@GetMapping("destinations/type/{description}")
	public List<Destination> findByDescription(HttpServletRequest req, HttpServletResponse res, @PathVariable String description) {
		
		try {
			System.out.print(description);
		List <Destination> destinations = destSrvc.findByType(description);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/destinations");
			return destinations;
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(400);
		}
		return null;
	}
	
	
	
	@GetMapping("destinations/addresses/{id}")
	public Destination findByAddress(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		
		try {
		Destination destination = destSrvc.findByAddressId(id);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/destinations" +destination.getId());
			return destination;
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(400);
		}
		return null;
	}
	
	
	@PostMapping("destinations")
	public Destination create(HttpServletRequest req, HttpServletResponse res, @RequestBody Destination destination) {
		try {
			destination = destSrvc.create(destination);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/destinations" +destination.getId());
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(400);
		}
		return destination;
	}
	
	
	@DeleteMapping("destinations/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		
		try {
			if (destSrvc.destroy(id)) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		

	}
	
	@PutMapping("destinations/{id}")
	public Destination update(HttpServletRequest req, HttpServletResponse res, @RequestBody Destination destination, @PathVariable int id) {
		try {
			 destination = destSrvc.update(destination, id);
			if(destination ==null) {
				res.setStatus(404);
			}
			}catch(Exception e){
				e.printStackTrace();
				res.setStatus(400);
				destination = null;
			}
			return destination;
	}
	
	
	
	

}

