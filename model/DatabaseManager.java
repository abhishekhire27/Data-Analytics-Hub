package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static DatabaseManager databaseManagerInstance = null;
	private String jdbcUrl = "jdbc:sqlite:/C:\\Semester 2\\Advanced Programming\\Assignments\\Assignment 2\\Data Analytics Hub\\DataAnalyticsDB.db";
	private Connection connection = null;
	
	private DatabaseManager() {
        try {
        	connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static DatabaseManager getInstance() {
        if (databaseManagerInstance == null) {
        	databaseManagerInstance = new DatabaseManager();
        }
        return databaseManagerInstance;
    }
	
	public Connection getConnection() {
		try {
			if(connection.isClosed()) {
				connection = DriverManager.getConnection(jdbcUrl);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
        return connection;
    }

}
