package com.skilldistillery.RainbowRoadtripPlanner.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Vehicle vehicle;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf=Persistence.createEntityManagerFactory("JPARainbowRoadtripPlanner");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
				vehicle = em.find(Vehicle.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		vehicle = null;
	}

	@Test
	void test() {
		assertNotNull(vehicle);
//		assertEquals("carolla", vehicle.getModel());
		assertEquals("toyota", vehicle.getMake());
	}
	
	@Test
	void test_Vehicle_To_Trip_OTM() {
		assertNotNull(vehicle);
		assertNotNull(vehicle.getTrips());
		assertFalse(vehicle.getTrips().isEmpty());
	}
	
	@Test
	void test_Vehicle_User_MTO() {
		assertNotNull(vehicle);
		assertNotNull(vehicle.getUser());
		assertEquals("bob", vehicle.getUser().getFirstName());
	}

}
