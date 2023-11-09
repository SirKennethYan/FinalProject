package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Comment;

public interface CommentService {
	
	public Comment show(String username, int tripId, int commentId);
	
	public List<Comment> showAllTripComments(int id);
	
	public Comment create(String username, Comment comment, int tripId);
	
	public Comment update(String username, int commentId, Comment comment, int tripId);
	
	public boolean destroy(String username, int commentId, int tripId);

}