package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocialMediaPost {
	private long postId;
	private String content;
	private String author;
	private long likes;
	private long shares;
	private LocalDateTime dateTime;
	private static int postNumber;
	
	//Default constructor to create the object with default values incas no value is passed
	public SocialMediaPost() {
		// THe social media post id will get auto incremented. Hence used it as static, so that only 1 copy of it will be created.
		SocialMediaPost.postNumber++;
		this.postId = SocialMediaPost.postNumber;
		this.content = "";
		this.author = "";
		this.likes = 0;
		this.shares = 0;
		this.dateTime = LocalDateTime.now();
	}
	
	//Parameterized constructor
	public SocialMediaPost(long postId, String content, String author, long likes, long shares,
			LocalDateTime dateTime) {
		super();
		this.postId = postId;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.dateTime = dateTime;
	}

	// Getter setters of the SocialMediaPost class
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
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
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public long getShares() {
		return shares;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
		return postId + "," + content + "," + author + "," + likes + "," + shares + "," + dateTime.format(formatter);
	}
	
}
