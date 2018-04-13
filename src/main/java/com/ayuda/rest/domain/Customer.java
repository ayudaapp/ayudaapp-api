package com.ayuda.rest.domain;

import java.util.Date;

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
@Table(name = "customer")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "", propOrder = { "custid", "fname", "lname", "phone", "email",
		"zipcode", "communityname", "zipcode", "password", "dob" })
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	@Column(name = "CUSTID", nullable = false)
	private long custId;

	@XmlAttribute
	@Column(name = "FNAME")
	private String firstName;

	@XmlAttribute
	@Column(name = "LNAME")
	private String lastName;

	@Column(name = "DOB")
	@XmlAttribute
	private Date dob;

	@Column(name = "PHONE")
	@XmlAttribute
	private String phone;

	@Column(name = "EMAIL")
	@XmlAttribute
	private String email;

	@XmlAttribute
	@Column(name = "ZIPCODE")
	private String zipCode;

	@XmlAttribute
	@Column(name = "PASSWORD")
	private String password;
	
	@XmlAttribute
	@Column(name = "COMMUNITYNAME")
	private String communityName;
	
	@XmlAttribute
	@Column(name = "USERID")
	private String userId;
	
	@XmlAttribute
	@Column(name = "RATING")
	private Double rating;
	
	@XmlAttribute
	@Column(name = "TOTALCHORES")
	private String totalChores;

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getTotalChores() {
		return totalChores;
	}

	public void setTotalChores(String totalChores) {
		this.totalChores = totalChores;
	}

	@Override
	public String toString() {
		return "Customer {" + "custId=" + custId + ", fname='" + firstName
				+ '\'' + ", lname='" + lastName + '\'' + ", phone='" + phone
				+ '\'' + ", zipcode=" + zipCode + ", email=" + email + ", dob="
				+ dob + '}';
	}
}
