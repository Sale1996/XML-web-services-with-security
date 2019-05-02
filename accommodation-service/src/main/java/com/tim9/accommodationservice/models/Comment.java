//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Comment")
@Entity
@Table( name= "comments" )
public class Comment {

    @XmlElement(name = "Comment_id")
    @Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id" )
    protected Long commentId;
    
    
    @XmlElement(name = "Comment_body", required = true)
    @Column ( name="comment_body", nullable = false )   
    protected String commentBody;
    
    
    @XmlElement(name = "Is_approved")
    @Column ( name="is_approved", nullable = false )
    protected boolean isApproved;
    
    
    @XmlElement(name = "Accommodation", required = true)
    @ManyToOne ()
	@JoinColumn (name="accommodation",nullable = false)
    protected Accommodation accommodation;
    
    
    @XmlElement(name = "Client", required = true)
    @Column ( name="client_id", nullable = false )    
    protected Long clientId;

   
    public long getCommentId() {
        return commentId;
    }

   
    public void setCommentId(long value) {
        this.commentId = value;
    }

  
    public String getCommentBody() {
        return commentBody;
    }

  
    public void setCommentBody(String value) {
        this.commentBody = value;
    }

 
    public boolean isIsApproved() {
        return isApproved;
    }

  
    public void setIsApproved(boolean value) {
        this.isApproved = value;
    }

 
    public Accommodation getAccommodation() {
        return accommodation;
    }

  
    public void setAccommodation(Accommodation value) {
        this.accommodation = value;
    }

  
    public Long getClient() {
        return clientId;
    }

  
    public void setClient(Long value) {
        this.clientId = value;
    }

}
