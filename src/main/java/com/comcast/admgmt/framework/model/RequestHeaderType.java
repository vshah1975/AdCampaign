
package com.comcast.admgmt.framework.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Control Information Sent with Requests to the
 * 				SSML
 * 				as part of the SOAP Envelope Header
 * 			
 * 
 * <p>Java class for RequestHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestHeaderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="trackingID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sequenceID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/&gt;
 *         &lt;element name="sourceServerID" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/&gt;
 *         &lt;element name="sourceSystemID" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeaderType", propOrder = {
    "trackingID",
    "sequenceID",
    "timestamp",
    "sourceServerID",
    "sourceSystemID"
})
public class RequestHeaderType {

    protected String trackingID;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequenceID;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String timestamp;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sourceServerID;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sourceSystemID;

    /**
     * Gets the value of the trackingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingID() {
        return trackingID;
    }

    /**
     * Sets the value of the trackingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingID(String value) {
        this.trackingID = value;
    }

    /**
     * Gets the value of the sequenceID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequenceID() {
        return sequenceID;
    }

    /**
     * Sets the value of the sequenceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequenceID(BigInteger value) {
        this.sequenceID = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the sourceServerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceServerID() {
        return sourceServerID;
    }

    /**
     * Sets the value of the sourceServerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceServerID(String value) {
        this.sourceServerID = value;
    }

    /**
     * Gets the value of the sourceSystemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystemID() {
        return sourceSystemID;
    }

    /**
     * Sets the value of the sourceSystemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystemID(String value) {
        this.sourceSystemID = value;
    }

}
