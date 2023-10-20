package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.DatabaseManager;
import model.SocialMediaPost;
import model.User;

public class DatabaseOperations {
    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    
    private static final DatabaseOperations databaseOperationsInstance = new DatabaseOperations();
    private Connection connection = databaseManager.getConnection();
    
    private static long latestUserId = 0;

    // Private constructor to prevent external instantiation
    private DatabaseOperations() {
   
    }

    public static DatabaseOperations getInstance() {
        return databaseOperationsInstance;
    }
    
    public void getLatestUserId() {
    	String sqlQuery = "SELECT MAX(userId) AS maxUserId FROM user";
    	 try {
             PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()) {
            	 DatabaseOperations.latestUserId = resultSet.getLong("maxUserId");
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

    	 DatabaseOperations.latestUserId = 0;
    }

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

    public boolean saveUserInDatabase(User user) {
    	boolean insertSuccess = false;
    	DatabaseOperations.latestUserId++;
        String sqlQuery = "INSERT INTO user VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setLong(1, DatabaseOperations.latestUserId);
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
            e.printStackTrace();
        }

        return insertSuccess;
    }
    
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
    
    public boolean addPost(SocialMediaPost post) {
    	boolean insertSuccess = false;

        String sqlQuery = "INSERT INTO socialmediapost VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, post.getPostId());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.setLong(4, post.getLikes());
            preparedStatement.setLong(5, post.getShares());
            preparedStatement.setString(6, post.getDateTime());
            preparedStatement.setLong(7, post.getUserId());
            
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                insertSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return insertSuccess;
    }
    
    public boolean removePost(String postId, long userId) {
    	boolean deleteSuccess = false;

        String sqlQuery = "DELETE FROM socialmediapost WHERE postId = ? AND userId = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, postId);
            preparedStatement.setLong(2, userId);
            
            int rowsDeleted  = preparedStatement.executeUpdate();

            if (rowsDeleted  > 0) {
            	deleteSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deleteSuccess;
    }
    
    public SocialMediaPost retrievePost(String postId, long userId) {
        
        String sqlQuery = "SELECT * FROM socialmediapost WHERE postId = ? AND userId = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, postId);
            preparedStatement.setLong(2, userId);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new SocialMediaPost(resultSet.getString("postId"), resultSet.getString("content"), resultSet.getString("author"), resultSet.getLong("likes"), resultSet.getLong("shares"), resultSet.getString("dateTime"), userId);
            }
            else {
            	return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<SocialMediaPost> getNLikesPost(int numberOfPosts, long userId){
    	String sqlQuery = "SELECT * FROM socialmediapost WHERE userId = ? ORDER BY likes DESC LIMIT ?";
    	ArrayList<SocialMediaPost> postList = new ArrayList<>();
    	
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, numberOfPosts);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            SocialMediaPost post = new SocialMediaPost();
            
            post.setPostId(resultSet.getString("postId"));
            post.setContent(resultSet.getString("content"));
            post.setAuthor(resultSet.getString("author"));
            post.setLikes(resultSet.getLong("likes"));
            post.setShares(resultSet.getLong("shares"));
            post.setDateTime(resultSet.getString("dateTime"));
            post.setUserId(resultSet.getLong("userId"));

            postList.add(post);

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    	
    }
    
    public boolean checkPostIdExists(String postId, long userId) {
        
        boolean postIdExists = false;
        String sqlQuery = "SELECT * FROM socialmediapost WHERE postId = ? AND userId = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, postId);
            preparedStatement.setLong(2, userId);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	postIdExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postIdExists;
    }
    
    public int getCountPostsInRange(int minimumShare, int maximumShare, long userId) {
    	String sqlQuery = "";
    	PreparedStatement preparedStatement = null;
    	try {
	    	if(maximumShare == -1) {
	    		sqlQuery = "SELECT COUNT(*) AS postCount FROM socialmediapost WHERE shares >= ? AND userId = ?";
	    		preparedStatement = this.connection.prepareStatement(sqlQuery);
	    		
	    		preparedStatement.setInt(1, minimumShare);
	            preparedStatement.setLong(2, userId);
	    	}
	    	else {
	    		sqlQuery = "SELECT COUNT(*) AS postCount FROM socialmediapost WHERE shares >= ? AND shares <= ? AND userId = ?";
	    		preparedStatement = this.connection.prepareStatement(sqlQuery);
	    		
	    		preparedStatement.setInt(1, minimumShare);
	    		preparedStatement.setInt(2, maximumShare);
	            preparedStatement.setLong(3, userId);
	    	}
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	return resultSet.getInt("postCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    	return 0;
    }
    
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
    
