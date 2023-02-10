package com.youtube.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.jwt.entity.Officer;
import com.youtube.jwt.service.OfficerService;

@RestController
public class OfficerController {

	
	@Autowired
	private OfficerService officerService;
	
	@GetMapping("/officer/{id}")
	public Officer getOfficer(@PathVariable(name = "id") String id) {
		Officer officerByOfficerId = officerService.getOfficerByOfficerId(id);
		return officerByOfficerId;
	}
	
	@PostMapping("/officer")
	public Officer createOfficer(@RequestBody Officer officer) {
		Officer registerNewUser = officerService.registerNewUser(officer);
		return registerNewUser;
	}
}
