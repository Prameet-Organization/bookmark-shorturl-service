package com.hackathon.bookmarkshorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.bookmarkshorturl.entity.BsGroup;

@Repository
public interface BSGroupRepository extends JpaRepository<BsGroup, Long> {

}
