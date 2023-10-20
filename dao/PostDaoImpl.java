package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SocialMediaPost;

public class PostDaoImpl implements PostDao {
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
    
    private static final PostDaoImpl databaseOperationsInstance = new PostDaoImpl();
    private Connection connection = databaseManager.getConnection();

    // Private constructor to prevent external instantiation
    private PostDaoImpl() {
   
    }

    public static PostDaoImpl getInstance() {
        return databaseOperationsInstance;
    }
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
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

}
