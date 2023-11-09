package com.skilldistillery.RainbowRoadtripPlanner.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
import com.skilldistillery.RainbowRoadtripPlanner.entities.User;
import com.skilldistillery.RainbowRoadtripPlanner.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public List<User> listAllUsers() {
		return userService.listAllUsers();
	}
	
	@GetMapping("users/id/{id}")
	public Optional<User> getUserById(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		return userService.findById(id);
	}
	
	
	@GetMapping("users/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	@PostMapping("users")
	public User create(Principal principal, HttpServletRequest req, HttpServletResponse res, @RequestBody User user) {
		try {
			user = userService.create(user);
			if (user != null) {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				res.setHeader("Location", url.append("/").append(user.getId()).toString());
			} else {
				res.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}
	
	@PutMapping("users/{id}")
	public User update(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id, @RequestBody User user) {
		User updatedTodo = null;
		try {
			updatedTodo = userService.update(id, user);
			if (updatedTodo == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updatedTodo;
	}
	
	@DeleteMapping("users/{id}")
	public void delete(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		try {
			if (userService.deleteById(id)) {
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
