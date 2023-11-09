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

class CommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

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
				comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	void test() {
		assertNotNull(comment);
		assertEquals("testing comment", comment.getDescription());
	}
	
	@Test
	void test_Comment_To_User_MTO() {
		assertNotNull(comment);
		assertNotNull(comment.getUser());
		assertEquals(1, comment.getUser().getId());
	}
	
	@Test
	void test_Comment_To_Trip_MTO() {
		assertNotNull(comment);
		assertNotNull(comment.getTrip());
		assertEquals(1, comment.getTrip().getId());
	}

}
