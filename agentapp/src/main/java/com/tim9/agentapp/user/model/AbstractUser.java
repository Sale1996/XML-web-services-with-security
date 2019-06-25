//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//

package com.tim9.agentapp.user.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractUser", propOrder = {
    "firstName",
    "lastName",
    "email",
    "password",
    "role"
})
@XmlSeeAlso({
    AgentLocal.class,
    User.class
})
@MappedSuperclass
public class AbstractUser {
		
	@Column()
	private Long id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	@XmlTransient
	private Long localId;
	
    @XmlElement(required = true)
    @Column
    protected String firstName;
    
    @XmlElement(required = true)
    @Column
    protected String lastName;
    
    @XmlElement(required = true)
    @Column
    protected String email;
    
    @XmlElement(required = true)
    @Column
    protected String password;
    
    @XmlElement(required = true)
    @Column
    protected String role;

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

	public Long getLocalId() {
		return localId;
	}

	public void setLocalId(Long localId) {
		this.localId = localId;
	}
}
