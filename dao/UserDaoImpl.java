package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import model.User;

public class UserDaoImpl implements UserDao {
	
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
    
    private static final UserDaoImpl databaseOperationsInstance = new UserDaoImpl();
    private Connection connection = databaseManager.getConnection();
    
    private static long latestUserId = 0;

    // Private constructor to prevent external instantiation
    private UserDaoImpl() {
    	this.getLatestUserId();
    }

    public static UserDaoImpl getInstance() {
        return databaseOperationsInstance;
    }
	
	@Override
	public void getLatestUserId() {
    	String sqlQuery = "SELECT MAX(userId) AS maxUserId FROM user";
    	 try {
             PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()) {
            	 UserDaoImpl.latestUserId = resultSet.getLong("maxUserId");
             }
         } catch (Exception e) {
//             e.printStackTrace();
             UserDaoImpl.latestUserId = 0;
         }
    }

	@Override
    public boolean checkUserNameExists(String userName) {
       
        boolean userNameExist = false;
        String sqlQuery = "SELECT * FROM user WHERE username = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, userName);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userNameExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userNameExist;
    }

    @Override
    public boolean saveUserInDatabase(User user) {
    	boolean insertSuccess = false;
    	UserDaoImpl.latestUserId++;
        String sqlQuery = "INSERT INTO user VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setLong(1, UserDaoImpl.latestUserId);
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setBoolean(6, user.isVipMember());
            
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                insertSuccess = true;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return insertSuccess;
    }
    
    @Override
    public boolean updateUserInDatabase(User user) {
    	boolean updateSuccess = false;
        String sqlQuery = "UPDATE user SET firstName = ?, lastName = ?, userName = ?, password = ?, isVipMember = ? WHERE userId = ?;";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.isVipMember());
            preparedStatement.setLong(6, user.getUserId());
            
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
            	updateSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updateSuccess;
    }

    @Override
    public HashMap<String, Object> loginUser(String userName, String password) {
        Connection connection = databaseManager.getConnection();

        boolean loginSuccess = false;
        User user = null;

        String sqlQuery = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loginSuccess = true;
                user = new User(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("userName"), resultSet.getString("password"));
                user.setUserId(resultSet.getLong("userId"));
                user.setVipMember(resultSet.getBoolean("isVipMember"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("loginSuccess", loginSuccess);
        returnMap.put("user", user);

        return returnMap;
    }

	@Override
	public boolean addVipMember(Long userId) {
    	boolean updateSuccess = false;
        String sqlQuery = "UPDATE user SET isVipMember = ? WHERE userId = ?;";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setBoolean(1, true);
            preparedStatement.setLong(2, userId);
            
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
            	updateSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updateSuccess;
    }
}
