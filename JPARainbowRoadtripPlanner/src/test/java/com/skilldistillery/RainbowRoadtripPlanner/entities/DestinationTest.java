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

class DestinationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Destination destination;

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
				destination = em.find(Destination.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		destination = null;
	}

	@Test
	void test() {
		assertNotNull(destination);
		assertEquals("food", destination.getDescription());
	}
	
	@Test
	void test_Destination_Accomodation_MTM() {
		assertNotNull(destination);
		assertNotNull(destination.getAccomodations());
		assertFalse(destination.getAccomodations().isEmpty());
	}
	
	@Test
	void test_Destination_Activity_MTO() {
		assertNotNull(destination);
		assertNotNull(destination.getActivity());
		assertFalse(destination.getActivity().isEmpty());
	}
	
	@Test
	void test_Destination_Address_OTO() {
		assertNotNull(destination);
		assertNotNull(destination.getAddress());
		assertEquals(1 ,destination.getAddress().getId());
	}
	

}
