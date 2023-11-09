package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Accomodation;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.AccomodationRepository;

@Service
public class AccomodationServiceImpl implements AccomodationService {

	
	@Autowired
	private AccomodationRepository accRepo;
	
	@Override
	public List<Accomodation> index() {
		// TODO Auto-generated method stub
		return accRepo.findAll();
	}

	@Override
	public Accomodation create(Accomodation accomodation) {
		if(accomodation != null) {
			return accRepo.saveAndFlush(accomodation);
		}
		return accomodation;
	}

	@Override
	public Accomodation update(Accomodation acccomodation, int id) {
		Accomodation toUpdate = accRepo.findById(id);
		if(toUpdate != null) {
			if(acccomodation.getDescription() != null) {
			toUpdate.setDescription(acccomodation.getDescription());
			}
			if(acccomodation.getName() != null) {
			toUpdate.setName(acccomodation.getName());
			}
			if(acccomodation.getIconUrl() != null) {
			toUpdate.setIconUrl(acccomodation.getIconUrl());
			}
			accRepo.saveAndFlush(toUpdate);
			return toUpdate;
		}
		return null;
	}

}
