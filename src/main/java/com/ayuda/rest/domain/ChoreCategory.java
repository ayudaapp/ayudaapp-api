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
@Table(name = "chorecategories")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "", propOrder = { "choreCategoryId", "choreCategoryDes" })
public class ChoreCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	@Column(name = "chorecat")
	private String choreCategoryId;

	@XmlAttribute
	@Column(name = "chorecatdes")
	private String choreCategoryDes;

	
	public String getChoreCategoryId() {
		return choreCategoryId;
	}


	public void setChoreCategoryId(String choreCategoryId) {
		this.choreCategoryId = choreCategoryId;
	}


	public String getChoreCategoryDes() {
		return choreCategoryDes;
	}


	public void setChoreCategoryDes(String choreCategoryDes) {
		this.choreCategoryDes = choreCategoryDes;
	}


	@Override
	public String toString() {
		return "ChoreCategory {" + "choreCategoryId=" + choreCategoryId + ", choreCategoryDes='" + choreCategoryDes
				 +'}';
	}
}
