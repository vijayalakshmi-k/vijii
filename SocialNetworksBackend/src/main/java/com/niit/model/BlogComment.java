package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="blogcomment")
public class BlogComment {
	@Id
	private int id;
	@ManyToOne
	private BlogPost blogPost;
	@ManyToOne
	private User commentBy;
	private String commentTxt;
	private Date commentedOn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BlogPost getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}
	public User getCommentBy() {
		return commentBy;
	}
	public void setCommentBy(User commentBy) {
		this.commentBy = commentBy;
	}
	public String getCommentTxt() {
		return commentTxt;
	}
	public void setCommentTxt(String commentTxt) {
		this.commentTxt = commentTxt;
	}
	public Date getCommentedOn() {
		return commentedOn;
	}
	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}
	@Override
	public String toString() {
		return "BlogComment [id=" + id + ", blogPost=" + blogPost + ", commentBy=" + commentBy + ", commentTxt="
				+ commentTxt + ", commentedOn=" + commentedOn + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}