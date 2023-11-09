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

class LegTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Leg leg;

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
				leg = em.find(Leg.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		leg = null;
	}

	@Test
	void test() {
		assertNotNull(leg);
		assertEquals("first leg", leg.getName());
	}
	
	@Test
	void test_Leg_To_Destination_MTO() {
		assertNotNull(leg);
		assertNotNull(leg.getEndDestination());
		assertNotNull(leg.getStartDestination());
		assertEquals(1, leg.getStartDestination().getId());
		assertEquals(2, leg.getEndDestination().getId());
		
	}
	
	@Test
	void test_Leg_To_Trip_MTO() {
		assertNotNull(leg);
		assertNotNull(leg.getTrip());
		assertEquals(1 , leg.getTrip().getId());
	}

}
