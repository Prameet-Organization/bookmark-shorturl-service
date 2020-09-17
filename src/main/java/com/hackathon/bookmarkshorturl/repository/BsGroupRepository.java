package com.hackathon.bookmarkshorturl.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.bookmarkshorturl.entity.BsGroup;
import com.hackathon.bookmarkshorturl.entity.User;

@Repository
public interface BsGroupRepository extends JpaRepository<BsGroup, Long> {
	
	List<BsGroup> findByGroupType(String type);
	
	List<BsGroup> findByGroupNameLikeIgnoreCase(String name);

	BsGroup findByGroupName(String groupName);

	Set<BsGroup> findByUsersIn(List<User> user);

}
