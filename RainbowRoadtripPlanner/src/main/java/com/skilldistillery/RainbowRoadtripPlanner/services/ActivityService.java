package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Activity;

public interface ActivityService {
	
	public List<Activity> index();
	public Activity create(Activity activity);
	public Activity update(Activity activity, int id);
	public boolean destroy(int id);
	public Activity findById(int id);
	
}
