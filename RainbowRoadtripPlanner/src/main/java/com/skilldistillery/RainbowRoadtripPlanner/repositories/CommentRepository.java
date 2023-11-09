package com.skilldistillery.RainbowRoadtripPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	Comment findById(int id);
	Comment findByIdAndTrip_Id(int commentId, int tripId);
	List<Comment> findByTripId(int id);
	Comment deleteById(int id);
	Comment findByIdAndUser_Username(int id, String username);
	
}