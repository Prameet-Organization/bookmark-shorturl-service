package com.hackathon.bookmarkshorturl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.bookmarkshorturl.entity.Url;
import com.hackathon.bookmarkshorturl.entity.User;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	
	List<Url> findByCreator(User user);
}
