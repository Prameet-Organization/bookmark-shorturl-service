package com.hackathon.bookmarkshorturl.dto;

public class BsGroupDto {
	
	private long id;
	
	private String groupType;
	
	private String groupName;
	
	private String username;
	
	private String shortUrl;
	
	private BsGroupDto tribe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BsGroupDto getTribe() {
		return tribe;
	}

	public void setTribe(BsGroupDto tribe) {
		this.tribe = tribe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
}
