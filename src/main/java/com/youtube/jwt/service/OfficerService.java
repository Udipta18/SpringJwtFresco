package com.youtube.jwt.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.youtube.jwt.dao.OfficerDao;
import com.youtube.jwt.dao.RoleDao;
import com.youtube.jwt.entity.Officer;
import com.youtube.jwt.entity.Role;
import com.youtube.jwt.entity.User;

@Service
public class OfficerService {
	
	
	@Autowired
	private OfficerDao officerDao;
	
	@Autowired
	private RoleDao roleDao;
	
	 @Autowired
	   private PasswordEncoder passwordEncoder;

	
	public Officer getOfficerByOfficerId(String id) {
		Optional<Officer> findByOfficerId = officerDao.findByOfficerId(id);
		if(findByOfficerId.isPresent()) {
			Officer officer = findByOfficerId.get();
			return officer;
		}
		
		return null;
	}
	
	public Officer registerNewUser(Officer officer) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        officer.setRole(userRoles);
        officer.setOfficerPassword(getEncodedPassword(officer.getOfficerPassword()));

        return officerDao.save(officer);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
