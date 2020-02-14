package com.micocci.twitterchallenge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweet")
public class Tweet {

	@Id
	private long id;

	private String hashTag;
	private String text;
	private long userId;
	private String userName;
	private Integer userFollowersCount;
	private String payloadReturned;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserFollowersCount() {
		return userFollowersCount;
	}

	public void setUserFollowersCount(Integer userFollowersCount) {
		this.userFollowersCount = userFollowersCount;
	}

	public String getPayloadReturned() {
		return payloadReturned;
	}

	public void setPayloadReturned(String payloadReturned) {
		this.payloadReturned = payloadReturned;
	}

}