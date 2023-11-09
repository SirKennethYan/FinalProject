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

class ActitivtyRatingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ActivityRating activityRating;

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
				activityRating = em.find(ActivityRating.class, new ActivityRatingId(1,1));
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		activityRating = null;
	}

	@Test
	void test() {
		assertNotNull(activityRating);
		assertEquals(5, activityRating.getRating());
		assertEquals("best ever", activityRating.getRatingComment());
	}

}
