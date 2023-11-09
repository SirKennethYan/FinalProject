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

class ActivityTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Activity activity;

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
		activity = em.find(Activity.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		activity = null;
	}

	@Test
	void test() {
		assertNotNull(activity);
		assertEquals("lunch break", activity.getDescription());
	}
	
	@Test
	void test_Activity_Destination_MTO() {
		assertNotNull(activity);
		assertNotNull(activity.getDestinations());
		assertEquals(1, activity.getDestinations().getId());
	}

}
