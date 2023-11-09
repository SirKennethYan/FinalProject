package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Destination;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.DestinationRepository;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationRepository destinationRepo;

	@Override
	public List<Destination> index() {
		// TODO Auto-generated method stub
		return destinationRepo.findAll();
	}
	@Override
	public Destination findById(int id) {
		// TODO Auto-generated method stub
		return destinationRepo.findById(id);
	}

	@Override
	public Destination findByAddressId(int id) {
		// TODO Auto-generated method stub
		return destinationRepo.findByAddress_Id(id);
	}

	@Override
	public Destination create(Destination destination) {
		if(destination != null) {
			destinationRepo.saveAndFlush(destination);
		}
		return destination;
	}

	@Override
	public boolean destroy(int id) {
		boolean didDelete = false;
		Destination toDelete =destinationRepo.findById(id);
		destinationRepo.delete(toDelete);
				if(!destinationRepo.existsById(id)) {
					didDelete = true;
					}
			
			
			return didDelete;
	}

	@Override
	public Destination update(Destination dest, int id) {
		Destination toUpdate = destinationRepo.findById(id);
		if(toUpdate != null) {
			if(dest.getName() != null) {
				toUpdate.setName(dest.getName());
			}
			if(dest.getAddress() != null) {
				toUpdate.setAddress(dest.getAddress());
			}
			if(dest.getDescription() != null) {
				toUpdate.setDescription(dest.getDescription());
			}
			if(dest.getImageUrl() != null) {
				toUpdate.setImageUrl(dest.getImageUrl());
			}
			if(dest.getNotes() != null) {
				toUpdate.setNotes(dest.getNotes());
			}
			if(dest.getPhone() != null) {
				toUpdate.setPhone(dest.getPhone());
			}
			if(dest.getFee() != null) {
				toUpdate.setFee(dest.getFee());
			}
			destinationRepo.saveAndFlush(toUpdate);
			return toUpdate;
		}
		return null;
	}
	@Override
	public List<Destination> findByType(String type) {
		// TODO Auto-generated method stub
		type = "%" + type +"%";
		return destinationRepo.findByDescriptionLike(type);
	}

	
	
	
	
	
}
