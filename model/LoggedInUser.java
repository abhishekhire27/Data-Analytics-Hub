package model;

// SIngleton class to get the single instance of the logged in user
public class LoggedInUser {
    private static User user;

    // Private constructor to prevent external instantiation
    private LoggedInUser(User user) {
        this.user = user;
    }

    // Private static instance variable
    private static LoggedInUser loggedInUserInstance = null;

    // Public method to get the single instance
    public static LoggedInUser setInstance(User user) {
    	loggedInUserInstance = new LoggedInUser(user);
        return loggedInUserInstance;
    }

    public static User getLoggedInUser() {
    	return user;
    }
    
    public static void logout() {
    	LoggedInUser.user = null;
    	loggedInUserInstance = null;
    }
}
