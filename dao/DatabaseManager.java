package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	
	private static DatabaseManager databaseManagerInstance = null;
	// relative path to the DB
	private String jdbcUrl = "jdbc:sqlite:..\\DataAnalyticsDB.db";
//	private String jdbcUrl = "jdbc:sqlite:/C:\\Semester 2\\Advanced Programming\\Assignments\\Assignment 2\\Data Analytics Hub\\DataAnalyticsDB.db";
	private Connection connection = null;
	
	private DatabaseManager() {
        try {
        	//This is a singleton class so that no multiple instances of database manager gets created
        	connection = DriverManager.getConnection(jdbcUrl);
        	Statement statement = connection.createStatement();
        	// Create table if it doesn't exists
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (userId INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, userName TEXT UNIQUE, password TEXT, isVipMember BOOLEAN);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS socialmediapost (postId TEXT, content TEXT, author TEXT, likes INTEGER, shares INTEGER, dateTime TEXT, userId INTEGER, PRIMARY KEY (postId, userId));");
        } catch (SQLException e) {
            	 e.printStackTrace();
        }
    }
	
	// Return the instance if instance is null
	public static DatabaseManager getInstance() {
        if (databaseManagerInstance == null) {
        	databaseManagerInstance = new DatabaseManager();
        }
        return databaseManagerInstance;
    }
	
	//Function to get the connection
	public Connection getConnection() {
		try {
			if(connection.isClosed()) {
				connection = DriverManager.getConnection(jdbcUrl);
				Statement statement = connection.createStatement();
	            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (userId INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, userName TEXT UNIQUE, password TEXT, isVipMember BOOLEAN);");
	            statement.executeUpdate("CREATE TABLE IF NOT EXISTS socialmediapost (postId TEXT PRIMARY KEY, content TEXT, author TEXT, likes TEXT, shares TEXT, dateTime TEXT, userId INTEGER);");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
        return connection;
    }

}
