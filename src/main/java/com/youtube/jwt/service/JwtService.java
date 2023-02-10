package com.youtube.jwt.service;

import com.youtube.jwt.configuration.JwtRequestFilter;
import com.youtube.jwt.dao.OfficerDao;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.JwtRequest;
import com.youtube.jwt.entity.JwtRequestForOfficer;
import com.youtube.jwt.entity.JwtResponse;
import com.youtube.jwt.entity.JwtResponseForOfficer;
import com.youtube.jwt.entity.Officer;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

	 Logger logger = LoggerFactory.getLogger(JwtService.class);
	
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private OfficerDao officerDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        logger.info("1");
        User user = userDao.findById(userName).get();
        return new JwtResponse(user, newGeneratedToken);
    }
    
    public JwtResponseForOfficer createJwtTokenForOfficer(JwtRequestForOfficer jwtRequest) throws Exception {
        String userName = jwtRequest.getOfficerId();
        String userPassword = jwtRequest.getOfficerPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = lodUserForOfficer(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        logger.info("2");
        Officer officer = officerDao.findByOfficerId(userName).get();
        return new JwtResponseForOfficer(officer, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	  
    	  
    	  if(Character.isDigit(username.charAt(0))){
    		  UserDetails lodUserForOfficer = lodUserForOfficer(username);
    		  return lodUserForOfficer;
    	  }
    	  
    	  logger.info("3"); 
        User user = userDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    
    public UserDetails lodUserForOfficer(String officerId) {
    	  logger.info("4");
    	 Officer officer = officerDao.findByOfficerId(officerId).get();
    	 
    	 if(officer!=null) {
    		 return new org.springframework.security.core.userdetails.User(
    				 officer.getOfficerId(),
                     officer.getOfficerPassword(),
                     getAutorityForOfficer(officer)
                     );
    	 }
    	 else {
             throw new UsernameNotFoundException("Officer not found with OfficerId: " + officerId);
         }
    }
    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
    
    private Set getAutorityForOfficer(Officer officer) {
    	Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    	officer.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
