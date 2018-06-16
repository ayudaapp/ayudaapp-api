package com.ayuda.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "chores")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "", propOrder = {
		"id",
		"type",
		"description",
		"date",
		"time",
		"choreCreator",
		"choreHelper",
		"price",
		"status",
		"repeat",
		"consent"
		
})
public class Chore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    @Column(name="ID", nullable = false)
    private long id;

    @XmlAttribute
    @Column(name="CHORETYPE")
    private String type;

    @XmlAttribute
    @Column(name="DESCRIPTION")
    private String description;
    
    @Column(name="CHOREDATE")
    
    @XmlAttribute
    private String date;
    
    @Column(name="time")
    @XmlAttribute
    private String time;
    
    @Column(name="CUSTID")
    @XmlAttribute    
    private String choreCreator;
    
    @XmlAttribute
    @Column (name="CHOREPRICE")
    private Double price;
    
    //@Column
    private int status;
    
    @XmlAttribute
    @Column(name="REPEAT")
    private String repeat;
    
    @XmlAttribute
    @Column(name="CONSENT")
    private String consent;
    
    @XmlAttribute
    @Column(name="CHROREHLPR")
    private String choreHeler;
    
       
    
    public String getChoreHeler() {
		return choreHeler;
	}

	public void setChoreHeler(String choreHeler) {
		this.choreHeler = choreHeler;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCustomerId() {
		return choreCreator;
	}

	public void setCustomerId(String customerId) {
		this.choreCreator = customerId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	   
    public Chore() {
    }

    public Chore(String name, String description, int rating) {
        this.type = name;
        this.description = description;
      
    }

    

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChoreCreator() {
		return choreCreator;
	}

	public void setChoreCreator(String choreCreator) {
		this.choreCreator = choreCreator;
	}

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    @Override
    public String toString() {
        return "Chore {" +
                "id=" + id +
                ", name='" + type + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", choreCreator=" + choreCreator +
                ", status=" + status +
                 ", price=" + price +
                 ", time=" + time +
                  ", date=" + date +
                  ", repeat=" + repeat +
                '}';
    }
}
