package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.User;
import com.skilldistillery.RainbowRoadtripPlanner.entities.Vehicle;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.UserRepository;
import com.skilldistillery.RainbowRoadtripPlanner.repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Vehicle> showAll(String username) {
		return vehicleRepo.findByUser_Username(username);
	}
	
	@Override
	public Vehicle show(String username, int id) {
		return vehicleRepo.findById(id);
	}

	@Override
	public Vehicle create(String username, Vehicle vehicle) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			vehicle.setMake(vehicle.getMake());
			vehicle.setModel(vehicle.getModel());
			vehicle.setEstimatedMPG(vehicle.getEstimatedMPG());
			vehicle.setEstimatedRange(vehicle.getEstimatedRange());
			vehicle.setCapacity(vehicle.getCapacity());
			vehicle.setIsElectric(vehicle.getIsElectric());
			return vehicleRepo.saveAndFlush(vehicle);
		} else {
			return null;
		}

	}

	@Override
	public Vehicle update(String username, int id, Vehicle vehicle) {
		Vehicle existing = vehicleRepo.findByIdAndUser_Username(id, username);
		if (existing != null) {
			vehicle.setMake(vehicle.getMake());
			vehicle.setModel(vehicle.getModel());
			vehicle.setEstimatedMPG(vehicle.getEstimatedMPG());
			vehicle.setEstimatedRange(vehicle.getEstimatedRange());
			vehicle.setCapacity(vehicle.getCapacity());
			vehicle.setIsElectric(vehicle.getIsElectric());
			return vehicleRepo.saveAndFlush(vehicle);
		} else {
			return null;
		}
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		Vehicle toDelete = vehicleRepo.findByIdAndUser_Username(id, username);
		if(toDelete != null) {
			vehicleRepo.delete(toDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<Vehicle> findUsersVehicles(int id) {
		// TODO Auto-generated method stub
		return vehicleRepo.findByUser_Id(id);
	}

}
