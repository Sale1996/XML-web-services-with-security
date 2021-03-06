package com.tim9.userserviceClient.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "firstName",
    "lastName",
    "email",
    "password",
    "role"
})
@XmlRootElement(name = "User")
@JsonIgnoreProperties(value = "password", allowGetters=false, allowSetters=true)
public class AdminDTO {
	
	@XmlElement(name = "id")
	protected Long id;
	
    @XmlElement(name = "firstName")
    protected String firstName;
    
    @XmlElement(name = "lastName")
    protected String lastName;
    
    @XmlElement(name = "email")
    protected String email;
    
    @XmlElement(name = "password")
    protected String password;
    
    @XmlElement(name = "role")
    protected String role;
    
    public AdminDTO() {}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    	
}
