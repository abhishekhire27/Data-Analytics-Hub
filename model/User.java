package model;

public class User {
	private long userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private boolean isVipMember;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.isVipMember= false;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isVipMember() {
		return isVipMember;
	}
	public void setVipMember(boolean isVipMember) {
		this.isVipMember = isVipMember;
	}

}
