package com.youtube.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtube.jwt.entity.Officer;

public interface OfficerDao extends JpaRepository<Officer, Integer>{
	
	Optional<Officer> findByOfficerId(String id);

}
