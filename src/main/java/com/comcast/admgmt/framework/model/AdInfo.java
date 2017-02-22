
package com.comcast.admgmt.framework.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="partner_id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="ad_content" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ad_expiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "partner_id",
    "duration",
    "ad_content",
    "ad_expiration",
    "status"
})
@XmlRootElement(name = "AdInfo")
public class AdInfo {

    @XmlElement(name = "partner_id", required = true)
    protected String partnerId;
    @XmlElement(required = true)
    protected BigInteger duration;
    @XmlElement(name = "ad_content", required = true)
    protected String adContent;
    @XmlElement(name = "ad_expiration")
    protected String adExpiration;
    @XmlElement(name = "status")
    protected String status;

    /**
     * Gets the value of the partnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerId(String value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuration(BigInteger value) {
        this.duration = value;
    }

    /**
     * Gets the value of the adContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdContent() {
        return adContent;
    }

    /**
     * Sets the value of the adContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdContent(String value) {
        this.adContent = value;
    }

    /**
     * Gets the value of the adExpiration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdExpiration() {
        return adExpiration;
    }

    /**
     * Sets the value of the adExpiration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdExpiration(String value) {
        this.adExpiration = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
