package test;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.PostDaoImpl;


public class TestPostDao {
	
	PostDaoImpl postDao = PostDaoImpl.getInstance();
	
	@Before
	public void setUp() throws Exception {
		// Nothing to do before every test case
	}

	@After
	public void tearDown() {
		System.setErr(System.err);
		System.setOut(System.out);
	}
	
	@Test
	public void testRemovePost_validPost() {
		assertEquals(true, postDao.removePost("2", 0));
	}
	
	@Test
	public void testRemovePost_invalidPost() {
		assertEquals(false, postDao.removePost("10", 0));
	}

	@Test
	public void testPostIdExists_validPost() {
		assertEquals(true, postDao.checkPostIdExists("2", 0));
	}
	
	@Test
	public void testPostIdExists_invalidPost() {
		//Post Id doesn't exist
		assertEquals(false, postDao.checkPostIdExists("10", 0));
	}
}
