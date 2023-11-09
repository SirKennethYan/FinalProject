package com.skilldistillery.RainbowRoadtripPlanner.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Accomodation;
import com.skilldistillery.RainbowRoadtripPlanner.services.AccomodationService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class AccomodationController {
	
	@Autowired
	private AccomodationService accSrvc;
	
	
	@GetMapping("accomodations")
	public List<Accomodation> index(){
		return accSrvc.index();
	}
	
	
	@PostMapping("accomodations")
	public Accomodation create(HttpServletRequest req, HttpServletResponse res, @RequestBody Accomodation accomodation) {
		try {
			accomodation = accSrvc.create(accomodation);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/accomodations" +accomodation.getId());
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(400);
		}
		return accomodation;
	}
	
	@PutMapping("accomodations/{id}")
	public Accomodation update(HttpServletRequest req, HttpServletResponse res, @RequestBody Accomodation accomodation, @PathVariable int id) {
		try {
			 accomodation = accSrvc.update(accomodation, id);
			if(accomodation ==null) {
				res.setStatus(404);
			}
			}catch(Exception e){
				e.printStackTrace();
				res.setStatus(400);
				accomodation = null;
			}
			return accomodation;
	}
	

}
