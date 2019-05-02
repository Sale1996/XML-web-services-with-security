//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accommodationUnitId",
    "description",
    "xCord",
    "yCord",
    "numberOfDaysBeforeCancelation"
})
@XmlRootElement(name = "Accommodation")
@Entity
@Table( name= "acccomodations" )
public class Accommodation {

    @XmlElement(name = "Accommodation_unit_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long accommodationUnitId;
    
    
    @XmlElement(name = "Description", required = true)
    @Column ( name="description", nullable = false )
    protected String description;
    
    
    @XmlElement(name = "X_cord")
    @Column ( name="x_cord", nullable = false )
    protected double xCord;
    
    
    @XmlElement(name = "Y_cord")
    @Column ( name="y_cord", nullable = false )
    protected double yCord;
    
    
    @XmlElement(name = "Number_of_days_before_cancelation")
    @Column ( name="number_of_days_before_cancelation", nullable = false )   
    protected int numberOfDaysBeforeCancelation;

    @XmlElement(name = "Accommodation_units")
    @OneToMany (mappedBy="accommodation")
    protected List<AccommodationUnit> units;
    
	@XmlElement(name ="Accommodation_comments")
	@OneToMany (mappedBy="accommodation")
    protected List<Comment> comments;
	
	@XmlElement(name = "Accommodation_pictures")
	@OneToMany (mappedBy="accommodation")
	protected List<Picture> pictures;

    
    public long getAccommodationUnitId() {
        return accommodationUnitId;
    }

    public void setAccommodationUnitId(long value) {
        this.accommodationUnitId = value;
    }

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }


    public double getXCord() {
        return xCord;
    }


    public void setXCord(double value) {
        this.xCord = value;
    }


    public double getYCord() {
        return yCord;
    }


    public void setYCord(double value) {
        this.yCord = value;
    }


    public int getNumberOfDaysBeforeCancelation() {
        return numberOfDaysBeforeCancelation;
    }


    public void setNumberOfDaysBeforeCancelation(int value) {
        this.numberOfDaysBeforeCancelation = value;
    }

}
