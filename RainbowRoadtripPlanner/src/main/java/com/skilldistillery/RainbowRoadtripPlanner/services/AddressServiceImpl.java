package com.skilldistillery.RainbowRoadtripPlanner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.RainbowRoadtripPlanner.entities.Address;

import com.skilldistillery.RainbowRoadtripPlanner.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Address> index() {
		// TODO Auto-generated method stub
		return addressRepo.findAll();
	}

	@Override
	public Address create(Address address) {
		if (address != null) {
			return addressRepo.saveAndFlush(address);
		}
		return address;
	}

	@Override
	public Address update(Address address, int id) {
		Address toUpdate = addressRepo.findById(id);
		if (toUpdate != null) {
			toUpdate.setStreet(address.getStreet());
			toUpdate.setStreet2(address.getStreet2());
			toUpdate.setCity(address.getCity());
			toUpdate.setState(address.getState());
			toUpdate.setZip(address.getZip());
			toUpdate.setActive(address.getActive());
			
			addressRepo.saveAndFlush(toUpdate);
			return toUpdate;
		}
		return null;
	}

	@Override
	public boolean destroy(int id) {
		boolean didDelete = false;
		Address toDelete = addressRepo.findById(id);
		addressRepo.delete(toDelete);
		if (!addressRepo.existsById(id)) {
			didDelete = true;
		}

		return didDelete;
	}

	@Override
	public List<Address> findByState(String state) {
		// TODO Auto-generated method stub
		return addressRepo.findByState(state);
	}

}
