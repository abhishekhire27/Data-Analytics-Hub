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
    private Connection connection = databaseManager.getConnection();;

    // Private constructor to prevent external instantiation
    private DatabaseOperations() {
   
    }

    public static DatabaseOperations getInstance() {
        return databaseOperationsInstance;
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

        String sqlQuery = "INSERT INTO user VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                insertSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return insertSuccess;
    }

    public HashMap<String, Object> loginUser(String userName, String password) {
        Connection connection = databaseManager.getConnection();

        boolean loginSuccess = false;
        User user = null;

        String sqlQuery = "SELECT * FROM User WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loginSuccess = true;
                user = new User(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("userName"), resultSet.getString("password"));
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

        String sqlQuery = "INSERT INTO socialmediapost VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, post.getPostId());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.setString(4, post.getLikes());
            preparedStatement.setString(5, post.getShares());
            preparedStatement.setString(6, post.getDateTime());
            
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                insertSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return insertSuccess;
    }
    
    public boolean removePost(String postId) {
    	boolean deleteSuccess = false;

        String sqlQuery = "DELETE FROM socialmediapost WHERE postId = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, postId);
            
            int rowsDeleted  = preparedStatement.executeUpdate();

            if (rowsDeleted  > 0) {
            	deleteSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deleteSuccess;
    }
    
    public SocialMediaPost retrievePost(String postId) {
        
        String sqlQuery = "SELECT * FROM socialmediapost WHERE postId = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, postId);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new SocialMediaPost(resultSet.getString("postId"), resultSet.getString("content"), resultSet.getString("author"), resultSet.getString("likes"), resultSet.getString("shares"), resultSet.getString("dateTime"));
            }
            else {
            	return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<SocialMediaPost> getNLikesPost(int numberOfPosts){
    	String sqlQuery = "SELECT * FROM socialmediapost ORDER BY likes DESC LIMIT ?";
    	ArrayList<SocialMediaPost> postList = new ArrayList<>();
    	
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setInt(1, numberOfPosts);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            SocialMediaPost post = new SocialMediaPost();
            
            post.setPostId(resultSet.getString("postId"));
            post.setContent(resultSet.getString("content"));
            post.setAuthor(resultSet.getString("author"));
            post.setLikes(resultSet.getString("likes"));
            post.setShares(resultSet.getString("shares"));
            post.setDateTime(resultSet.getString("dateTime"));

            postList.add(post);

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postList;
    	
    }
    
    public boolean checkPostIdExists(String postId) {
        
        boolean postIdExists = false;
        String sqlQuery = "SELECT * FROM socialmediapost WHERE postId = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            
            preparedStatement.setString(1, postId);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	postIdExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postIdExists;
    }
}
    
