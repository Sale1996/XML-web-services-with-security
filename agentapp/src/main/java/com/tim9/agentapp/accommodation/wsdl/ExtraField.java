//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.18 at 11:05:49 AM CEST 
//


package com.tim9.agentapp.accommodation.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for extraField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="extraField"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Extra_field_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="Extra_field_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Extra_price" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Optional" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
@XmlType(name = "extraField", propOrder = {
    "extraFieldId",
    "extraFieldName",
    "extraPrice",
    "optional",
    "lastUpdated"
})
public class ExtraField {

    @XmlElement(name = "Extra_field_id")
    protected Long extraFieldId;
    @XmlElement(name = "Extra_field_name", required = true)
    protected String extraFieldName;
    @XmlElement(name = "Extra_price")
    protected float extraPrice;
    @XmlElement(name = "Optional")
    protected boolean optional;
    @XmlElement(name = "LastUpdated")
    protected String lastUpdated;

    /**
     * Gets the value of the extraFieldId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExtraFieldId() {
        return extraFieldId;
    }

    /**
     * Sets the value of the extraFieldId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExtraFieldId(Long value) {
        this.extraFieldId = value;
    }

    /**
     * Gets the value of the extraFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraFieldName() {
        return extraFieldName;
    }

    /**
     * Sets the value of the extraFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraFieldName(String value) {
        this.extraFieldName = value;
    }

    /**
     * Gets the value of the extraPrice property.
     * 
     */
    public float getExtraPrice() {
        return extraPrice;
    }

    /**
     * Sets the value of the extraPrice property.
     * 
     */
    public void setExtraPrice(float value) {
        this.extraPrice = value;
    }

    /**
     * Gets the value of the optional property.
     * 
     */
    public boolean isOptional() {
        return optional;
    }

    /**
     * Sets the value of the optional property.
     * 
     */
    public void setOptional(boolean value) {
        this.optional = value;
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
