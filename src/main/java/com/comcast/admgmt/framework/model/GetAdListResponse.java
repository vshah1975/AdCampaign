
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
 *         &lt;element name="status_code" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="status_message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ad_list" type="{http://framework.admgmt.comcast.com/model}AdList"/&gt;
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
    "statusCode",
    "statusMessage",
    "adList"
})
@XmlRootElement(name = "GetAdListResponse")
public class GetAdListResponse {

    @XmlElement(name = "status_code", required = true)
    protected BigInteger statusCode;
    @XmlElement(name = "status_message")
    protected String statusMessage;
    @XmlElement(name = "ad_list", required = true)
    protected AdList adList;

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStatusCode(BigInteger value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Gets the value of the adList property.
     * 
     * @return
     *     possible object is
     *     {@link AdList }
     *     
     */
    public AdList getAdList() {
        return adList;
    }

    /**
     * Sets the value of the adList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdList }
     *     
     */
    public void setAdList(AdList value) {
        this.adList = value;
    }

}
