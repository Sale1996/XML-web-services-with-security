package com.tim9.userservice.models;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Admin", propOrder = {

})
@XmlRootElement(name = "Admin")
@Entity
public class Admin extends AbstractUser {
	
	
	
}
