package com.youtube.jwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.jwt.dao.ComplaintDao;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.Complaint;
import com.youtube.jwt.entity.User;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintDao complaintDao;
	
	@Autowired
	private UserDao userDao;
	
	public boolean registerComplaint(Complaint complaint,String name) {
		
		Optional<User> findByUserName = userDao.findByUserName(name);
		
		if(findByUserName.isPresent()) {
			User user = findByUserName.get();
			complaint.setUserId(user);
			complaintDao.save(complaint);
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
