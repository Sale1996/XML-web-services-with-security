//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.19 at 12:17:30 AM CEST 
//


package com.tim9.agentapp.accommodation.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="comment"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Comment_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="Comment_body" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Is_approved" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element ref="{http://tim9.com/accommodationService}Accommodation"/&gt;
 *         &lt;element name="Client" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="LastUpdated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comment", propOrder = {
    "commentId",
    "commentBody",
    "isApproved",
    "accommodation",
    "client",
    "lastUpdated"
})
public class Comment {

    @XmlElement(name = "Comment_id")
    protected Long commentId;
    @XmlElement(name = "Comment_body", required = true)
    protected String commentBody;
    @XmlElement(name = "Is_approved")
    protected boolean isApproved;
    @XmlElement(name = "Accommodation", required = true)
    protected Accommodation accommodation;
    @XmlElement(name = "Client")
    protected long client;
    @XmlElement(name = "LastUpdated")
    protected String lastUpdated;

    /**
     * Gets the value of the commentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * Sets the value of the commentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCommentId(Long value) {
        this.commentId = value;
    }

    /**
     * Gets the value of the commentBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentBody() {
        return commentBody;
    }

    /**
     * Sets the value of the commentBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentBody(String value) {
        this.commentBody = value;
    }

    /**
     * Gets the value of the isApproved property.
     * 
     */
    public boolean isIsApproved() {
        return isApproved;
    }

    /**
     * Sets the value of the isApproved property.
     * 
     */
    public void setIsApproved(boolean value) {
        this.isApproved = value;
    }

    /**
     * Gets the value of the accommodation property.
     * 
     * @return
     *     possible object is
     *     {@link Accommodation }
     *     
     */
    public Accommodation getAccommodation() {
        return accommodation;
    }

    /**
     * Sets the value of the accommodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accommodation }
     *     
     */
    public void setAccommodation(Accommodation value) {
        this.accommodation = value;
    }

    /**
     * Gets the value of the client property.
     * 
     */
    public long getClient() {
        return client;
    }

    /**
     * Sets the value of the client property.
     * 
     */
    public void setClient(long value) {
        this.client = value;
    }

    /**
     * Gets the value of the lastUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the value of the lastUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdated(String value) {
        this.lastUpdated = value;
    }

}
