package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Comment;
import com.skilldistillery.RainbowRoadtripPlanner.entities.Trip;
import com.skilldistillery.RainbowRoadtripPlanner.entities.User;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.CommentRepository;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.TripRepository;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TripRepository tripRepo;
	

	@Override
	public List<Comment> showAllTripComments(int id) {
		return commentRepo.findByTripId(id);
	}

	@Override
	public Comment show(String username, int tripId, int commentId) {
		return commentRepo.findByIdAndTrip_Id(commentId, tripId);
	}

	@Override
	public Comment create(String username, Comment comment, int tripId) {
		User user = userRepo.findByUsername(username);
		Trip trip = tripRepo.findById(tripId);
		if(user != null && trip != null) {
			comment.setUser(user);
			comment.setTrip(trip);
			return commentRepo.saveAndFlush(comment);
		}
		return null;
	}

	@Override
	public Comment update(String username, int commentId, Comment comment, int tripId) {
		Comment existing = commentRepo.findByIdAndUser_Username(commentId, username);
		if (existing != null) {
			existing.setPhoto(existing.getPhoto());
			existing.setDescription(existing.getDescription());
//			existing.setActive(existing.getActive());
			commentRepo.saveAndFlush(existing);
			return existing;
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int commentId, int tripId) {
		boolean deleted = false;
		Comment toDelete = commentRepo.findByIdAndTrip_Id(commentId, tripId);
		if(toDelete != null) {
			commentRepo.delete(toDelete);
			deleted = true;
		}
		return deleted;
	}

}