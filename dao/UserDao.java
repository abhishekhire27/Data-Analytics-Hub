package dao;

import java.util.HashMap;

import model.User;

public interface UserDao {
	void getLatestUserId();
	boolean checkUserNameExists(String userName);
	boolean saveUserInDatabase(User user);
	boolean updateUserInDatabase(User user);
	HashMap<String, Object> loginUser(String userName, String password);
	boolean addVipMember(Long userId);
}
