package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocialMediaPost {
	private String postId;
	private String content;
	private String author;
	private String likes;
	private String shares;
	private String dateTime;
	private static int postNumber;
	
	//Default constructor to create the object with default values incas no value is passed
//	public SocialMediaPost() {
		// THe social media post id will get auto incremented. Hence used it as static, so that only 1 copy of it will be created.
//		SocialMediaPost.postNumber++;
//		this.postId = SocialMediaPost.postNumber;
//		this.content = "";
//		this.author = "";
//		this.likes = 0;
//		this.shares = 0;
//		this.dateTime = LocalDateTime.now();
//	}
	
	public SocialMediaPost() {
		
	}
	
	//Parameterized constructor
	public SocialMediaPost(String postId, String content, String author, String likes, String shares,
			String dateTime) {
		super();
		this.postId = postId;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.dateTime = dateTime;
	}

	

	public String getPostId() {
		return postId;
	}



	public void setPostId(String postId) {
		this.postId = postId;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getLikes() {
		return likes;
	}



	public void setLikes(String likes) {
		this.likes = likes;
	}



	public String getShares() {
		return shares;
	}



	public void setShares(String shares) {
		this.shares = shares;
	}



	public String getDateTime() {
		return dateTime;
	}



	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}



	public static int getPostNumber() {
		return postNumber;
	}



	public static void setPostNumber(int postNumber) {
		SocialMediaPost.postNumber = postNumber;
	}



//	@Override
//	public String toString() {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
//		return postId + "," + content + "," + author + "," + likes + "," + shares + "," + dateTime.format(formatter);
//	}
	
}
