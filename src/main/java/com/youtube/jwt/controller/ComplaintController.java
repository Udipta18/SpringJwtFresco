package com.youtube.jwt.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.jwt.entity.Complaint;
import com.youtube.jwt.service.ComplaintService;

@RestController
@RequestMapping("/Complaint")
public class ComplaintController {
	
	
	@Autowired
	private ComplaintService complaintService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerComplaint(@RequestBody Complaint complaint,Principal principal){
		String name = principal.getName();
		boolean trueOrFalse = complaintService.registerComplaint(complaint, name);
		if(trueOrFalse) {
			return new ResponseEntity<>("Complaint Register Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);
	}

}
