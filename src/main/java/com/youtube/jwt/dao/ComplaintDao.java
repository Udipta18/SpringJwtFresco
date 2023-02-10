package com.youtube.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtube.jwt.entity.Complaint;

public interface ComplaintDao extends JpaRepository<Complaint, String>{

}
