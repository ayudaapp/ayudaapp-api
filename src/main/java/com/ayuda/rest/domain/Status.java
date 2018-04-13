package com.ayuda.rest.domain;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="status")
public class Status {
	private long statusCd = 0;
	//private String errorMessage;
	private String statusMessage;
	
	public long getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(long statusCd) {
		this.statusCd = statusCd;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
