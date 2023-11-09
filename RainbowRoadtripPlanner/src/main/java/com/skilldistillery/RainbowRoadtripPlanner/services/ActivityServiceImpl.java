package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Activity;

import com.skilldistillery.RainbowRoadtripPlanner.repositories.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository actRepo;

	@Override
	public List<Activity> index() {
		// TODO Auto-generated method stub
		return actRepo.findAll() ;
	}
	@Override
	public Activity findById(int id) {
		
		return actRepo.findById(id);
	}


	@Override
	public Activity create(Activity activity) {
		if(activity != null) {
			actRepo.saveAndFlush(activity);
		}
		return activity;
	}

	@Override
	public Activity update(Activity activity, int id) {
		Activity toUpdate = actRepo.findById(id);
		if(toUpdate != null) {
			if(activity.getLeg() !=null) {
				toUpdate.setLeg(activity.getLeg());
			}
			if(activity.getDestinations() !=null) {
				toUpdate.setLeg(activity.getLeg());
			}
			if(activity.getDidStop() !=null) {
				toUpdate.setDidStop(activity.getDidStop());;
			}
			if(activity.getDescription() !=null) {
				toUpdate.setDescription(activity.getDescription());
			}
			if(activity.getPriorityLevel() !=null) {
				toUpdate.setPriorityLevel(activity.getPriorityLevel());
			}
			if(activity.getTimeToSpend() !=null) {
				toUpdate.setTimeToSpend(activity.getTimeToSpend());
			}
			actRepo.saveAndFlush(toUpdate);
			return toUpdate;
			
		}
		return null;
	}

	@Override
	public boolean destroy(int id) {
		boolean didDelete = false;
		Activity toDelete =actRepo.findById(id);
		actRepo.delete(toDelete);
				if(!actRepo.existsById(id)) {
					didDelete = true;
					}
			
			
			return didDelete;
	}


	

}
