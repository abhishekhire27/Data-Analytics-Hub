package dao;

import java.util.ArrayList;

import model.SocialMediaPost;

public interface PostDao {
	boolean addPost(SocialMediaPost post);
	boolean removePost(String postId, long userId);
	SocialMediaPost retrievePost(String postId, long userId);
	ArrayList<SocialMediaPost> getNLikesPost(int numberOfPosts, long userId);
	boolean checkPostIdExists(String postId, long userId);
	int getCountPostsInRange(int minimumShare, int maximumShare, long userId);

}
