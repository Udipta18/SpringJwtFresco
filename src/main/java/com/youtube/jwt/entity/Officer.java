package com.youtube.jwt.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Officer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String officerId;
    private String Designation;
    private String officerPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "OFFICER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "OFFICER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
    
    
    
	public Officer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOfficerId() {
		return officerId;
	}
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public String getOfficerPassword() {
		return officerPassword;
	}
	public void setOfficerPassword(String officerPassword) {
		this.officerPassword = officerPassword;
	}
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	public Officer(Integer id, String officerId, String designation, String officerPassword, Set<Role> role) {
		super();
		this.id = id;
		this.officerId = officerId;
		Designation = designation;
		this.officerPassword = officerPassword;
		this.role = role;
	}
    
    
}
