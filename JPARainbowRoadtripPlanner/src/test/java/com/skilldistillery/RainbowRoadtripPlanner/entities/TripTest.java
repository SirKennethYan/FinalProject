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

class TripTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Trip trip;

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
				trip = em.find(Trip.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		trip = null;
	}

	@Test
	void test() {
		assertNotNull(trip);
		assertEquals("First trip", trip.getTitle());
	}
	
	@Test
	void test_Trip_Leg_OTM() {
		assertNotNull(trip);
		assertNotNull(trip.getLegs());
		assertFalse(trip.getLegs().isEmpty());
	}
	
	@Test
	void test_Trip_To_Vehicle_MTO() {
		assertNotNull(trip);
		assertNotNull(trip.getVehicle());
		assertEquals(1, trip.getVehicle().getId());
	}
	
	@Test
	void test_Trip_To_Comment_OTM() {
		assertNotNull(trip);
		assertNotNull(trip.getComments());
		assertFalse(trip.getComments().isEmpty());
	}


}
