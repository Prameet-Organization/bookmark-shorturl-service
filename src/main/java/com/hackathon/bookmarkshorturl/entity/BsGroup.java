package com.hackathon.bookmarkshorturl.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private List<BsGroup> features;
	
	@ManyToOne
	private BsGroup tribe;
	
	@OneToMany
	private List<User> teams;
	
	@OneToOne
	private User admin;

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

	public List<BsGroup> getFeatures() {
		return features;
	}

	public void setFeatures(List<BsGroup> features) {
		this.features = features;
	}

	public BsGroup getTribe() {
		return tribe;
	}

	public void setTribe(BsGroup tribe) {
		this.tribe = tribe;
	}

	public List<User> getTeams() {
		return teams;
	}

	public void setTeams(List<User> teams) {
		this.teams = teams;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}		
	
	
}
