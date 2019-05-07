package com.project.userservice.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.project.userservice.models.UserType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "surname",
    "username",
    "email",
    "telephone_number",
    "address",
    "type"
})
@XmlRootElement(name = "User")
public class UserDTO {
	
	@XmlElement(name = "id")
	protected Long id;
	
    @XmlElement(name = "name")
    protected String name;
    
    @XmlElement(name = "surname")
    protected String surname;
    
    @XmlElement(name = "username")
    protected String username;
    
    @XmlElement(name = "email")
    protected String email;
    
    @XmlElement(name = "telephone_number")
    protected String telephoneNumber;
    
    @XmlElement(name = "address")
    protected String address;
    
    @XmlElement(name = "type")
    protected UserType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
