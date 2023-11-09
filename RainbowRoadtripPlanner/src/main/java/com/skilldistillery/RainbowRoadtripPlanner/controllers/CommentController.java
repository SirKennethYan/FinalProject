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

import com.skilldistillery.RainbowRoadtripPlanner.entities.Comment;
import com.skilldistillery.RainbowRoadtripPlanner.services.CommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CommentController {

	@Autowired
	CommentService commentService;

	@GetMapping("trips/{tripId}/comments")
	public List<Comment> showAllforTrip(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable int tripId) {
		return commentService.showAllTripComments(tripId);
	}

	@GetMapping("trips/{tripId}/comments/{commentId}")
	public Comment show(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable int tripId,
			@PathVariable int commentId) {
		Comment findComment = commentService.show(principal.getName(), tripId, commentId);
		if (findComment == null) {
			res.setStatus(404);
		}
		return findComment;
	}

	@PostMapping("trips/{tripId}/comments")
	public Comment create(Principal principal, HttpServletRequest req, @RequestBody Comment comment,
			HttpServletResponse res, @PathVariable int tripId) {
		Comment createdComment = null;
		try {
			createdComment = commentService.create(principal.getName(), comment, tripId);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return createdComment;

	}

	@PutMapping("trips/{tripId}/comments/{commentId}")
	public Comment update(Principal principal, HttpServletRequest req, @RequestBody Comment comment,
			@PathVariable int commentId, @PathVariable int tripId, HttpServletResponse res) {
		Comment updated = null;
		try {
			updated = commentService.update(principal.getName(), commentId, comment, tripId);
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

	@DeleteMapping("trips/{tripId}/comments/{commentId}")
	public boolean destroy(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable int commentId, @PathVariable int tripId) {
		boolean deleted = false;

		try {
			deleted = commentService.destroy(principal.getName(), commentId, tripId);
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

}