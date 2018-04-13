package com.ayuda.rest.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/*
 * a simple domain entity doubling as a DTO
 */
/*@Entity
@Table(name = "chore")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)*/
public class AcceptChore {

	//@Column(nullable = false)
    private long choreId;

    //@Column
    private String comment;
    
      
    //@Column(nullable = false)
    private String customerId;
    
   // @Column(nullable = false)
    private String choreHelper;
    
     
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public AcceptChore() {
    }

      
    public long getChoreId() {
		return choreId;
	}

	public void setChoreId(long choreId) {
		this.choreId = choreId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getChoreHelper() {
		return choreHelper;
	}

	public void setChoreHelper(String choreHelper) {
		this.choreHelper = choreHelper;
	}

	public String getCustomerId() {
		return customerId;
	}

	@Override
    public String toString() {
        return "AcceptChore {" +
                "id=" + choreId +
                ", customerID='" + customerId + '\'' +
                ", choreHelper='" + choreHelper + '\'' +
                '}';
    }
}
