package test;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.UserDaoImpl;
import model.User;


public class TestUserDao {
	
	UserDaoImpl userDao = UserDaoImpl.getInstance();
	
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
	public void testUserNameExists_validUserName() {
		assertEquals(true, userDao.checkUserNameExists("abhishek"));
	}
	
	@Test
	public void testUserNameExists_invalidUserName() {
		assertEquals(false, userDao.checkUserNameExists("abhishek27"));
	}

	@Test
	public void testSaveUserInDatabase_validInput() {
		assertEquals(true, userDao.saveUserInDatabase(new User("Rohan","Joshi","rohan27", "abhishek27")));
	}
	
	@Test
	public void testSaveUserInDatabase_inValidInput() {
		//Repeated UserName
		assertEquals(false, userDao.saveUserInDatabase(new User("Rohan","Joshi","abhishek", "abhishek27")));
	}
}
