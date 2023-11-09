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

class AccomodationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Accomodation accomodation;

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
		accomodation = em.find(Accomodation.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		accomodation = null;
	}

	@Test
	void test() {
		assertNotNull(accomodation);
		assertEquals("Gas", accomodation.getName());
	}
	
	@Test
	void test_accomodation_destination_relational_manyToMany() {
		assertNotNull(accomodation);
		assertNotNull(accomodation.getDestinations());
		assertFalse(accomodation.getDestinations().isEmpty());
	}

}
