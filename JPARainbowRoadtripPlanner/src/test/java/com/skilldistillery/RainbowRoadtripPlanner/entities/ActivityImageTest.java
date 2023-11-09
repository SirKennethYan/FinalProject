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

class ActivityImageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ActivityImage activityImage;

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
		activityImage = em.find(ActivityImage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		activityImage = null;
	}

	@Test
	void test() {
		assertNotNull(activityImage);
		assertEquals("my first pic", activityImage.getCaption());
	}
}
