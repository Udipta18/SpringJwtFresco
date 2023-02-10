package com.youtube.jwt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer complaintId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User UserId;
	
	private String Description;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date complainedAt;

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public User getUserId() {
		return UserId;
	}

	public void setUserId(User userId) {
		UserId = userId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getComplainedAt() {
		return complainedAt;
	}

	public void setComplainedAt(Date complainedAt) {
		this.complainedAt = complainedAt;
	}
	
	
	
	

}
