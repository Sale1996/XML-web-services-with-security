//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:50:21 AM CEST 
//


package com.tim9.accommodationservice.dtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Comment")
public class CommentDTO {

    @XmlElement(name = "Comment_id")
    protected Long commentId;
    
    
    @XmlElement(name = "Comment_body", required = true)
    protected String commentBody;
    
    
    @XmlElement(name = "Is_approved")
    protected boolean isApproved;
    
    
    @XmlElement(name = "Accommodation", required = true)
    protected AccommodationDTO accommodation;
    
    
    @XmlElement(name = "Client", required = true)
    protected Long clientId;

   
    public Long getCommentId() {
        return commentId;
    }

   
    public void setCommentId(Long value) {
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

 
    public AccommodationDTO getAccommodation() {
        return accommodation;
    }

  
    public void setAccommodation(AccommodationDTO value) {
        this.accommodation = value;
    }

  
    public Long getClient() {
        return clientId;
    }

  
    public void setClient(Long value) {
        this.clientId = value;
    }

}
