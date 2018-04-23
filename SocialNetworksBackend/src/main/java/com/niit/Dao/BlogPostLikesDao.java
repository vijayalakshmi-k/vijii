package com.niit.Dao;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;

public interface BlogPostLikesDao {
	BlogPostLikes hasUserLikedBlog(int id, String email);

	BlogPost updateLikes(int id, String email);
}
