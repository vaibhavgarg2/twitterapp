package com.apple.twitter.integration.model;

import java.util.Date;

public class CustomTweet {
	private long id;
	private String text;
	private Date createdAt;
	private String fromUser;
	private long fromUserId;
	private Integer retweetCount;
	
	private CustomTweet(CustomTweetBuilder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.createdAt = builder.createdAt;
        this.fromUser = builder.fromUser;
        this.fromUserId = builder.fromUserId;
        this.retweetCount = builder.retweetCount;
    }
	
	public String getFromUser() {
		return fromUser;
	}
	public long getFromUserId() {
		return fromUserId;
	}
	public Integer getRetweetCount() {
		return retweetCount;
	}
	public long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public static class CustomTweetBuilder{
		private long id;
		private String text;
		private Date createdAt;
		private String fromUser;
		private long fromUserId;
		private Integer retweetCount;
		
		public CustomTweetBuilder setId(long id) {
			this.id = id;
			return this;
		}
		
		public CustomTweetBuilder setText(String text) {
			this.text = text;
			return this;
		}
		
		public CustomTweetBuilder setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public CustomTweetBuilder setFromUserId(long fromUserId) {
			this.fromUserId = fromUserId;
			return this;
		}
		
		public CustomTweetBuilder setFromUser(String fromUser) {
			this.fromUser = fromUser;
			return this;
		}
		
		public CustomTweetBuilder setRetweetCount(Integer retweetCount) {
			this.retweetCount = retweetCount;
			return this;
		}
		
		public CustomTweet build() {
			 	CustomTweet tweet =  new CustomTweet(this); 
	            return tweet;
		}
	}
	
	
}
