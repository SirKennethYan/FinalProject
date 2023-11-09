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

import com.skilldistillery.RainbowRoadtripPlanner.entities.Address;
import com.skilldistillery.RainbowRoadtripPlanner.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class AddressController {
	
	
	@Autowired
	AddressService addSrvc;
	
	
	@GetMapping("addresses")
	public List<Address> index(){
		return addSrvc.index();
	}
	
	@GetMapping("addresses/{state}")
	public List<Address> findByState(HttpServletRequest req, HttpServletResponse res, @PathVariable  String state) {
		return addSrvc.findByState(state);
	}
	
	
	@PostMapping("addresses")
	public Address create(HttpServletRequest req, HttpServletResponse res, @RequestBody Address address) {
				
		try {
			address = addSrvc.create(address);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/addresses" +address.getId());
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(400);
		}
		return address;
		
	}
	
	
	@PutMapping("addresses/{id}")
	public Address update( HttpServletRequest req, HttpServletResponse res, @PathVariable int id, @RequestBody Address address) {
		try {
			 address = addSrvc.update(address, id);
			if(address ==null) {
				res.setStatus(404);
			}
			}catch(Exception e){
				e.printStackTrace();
				res.setStatus(400);
				address = null;
			}
			return address;
	}
	
	@DeleteMapping("addresses/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		try {
			if (addSrvc.destroy(id)) {
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
	
	
	

}
