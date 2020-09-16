package com.hackathon.bookmarkshorturl.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "groupName")
})
public class BsGroup {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	
	private String groupType;
	
	private String groupName;

	@OneToMany
	private Set<BsGroup> features;
	
	@ManyToOne
	private BsGroup tribe;
	
	@OneToMany
	private Set<User> teams;
	
	@OneToOne
	private User admin;
	
	@ManyToMany
	private Set<Url> urls;
	
	@ManyToMany
	private Set<User> user;

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

	public BsGroup getTribe() {
		return tribe;
	}

	public void setTribe(BsGroup tribe) {
		this.tribe = tribe;
	}

	
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public Set<BsGroup> getFeatures() {
		return features;
	}

	public void setFeatures(Set<BsGroup> features) {
		this.features = features;
	}

	public Set<User> getTeams() {
		return teams;
	}

	public void setTeams(Set<User> teams) {
		this.teams = teams;
	}

	public Set<Url> getUrls() {
		return urls;
	}

	public void setUrls(Set<Url> urls) {
		this.urls = urls;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
}
