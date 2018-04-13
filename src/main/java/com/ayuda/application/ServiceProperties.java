package com.ayuda.application;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * demonstrates how service-specific properties can be injected
 */
//@ConfigurationProperties(prefix = "hotel.service", ignoreUnknownFields = false)
//@Component

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "database", ignoreUnknownFields = false)
public class ServiceProperties {

    private String environment;
    private String driverClassName;
    private String dbhost; 
    private String dbport;
    private String username;
    private String password;
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	public String getDbhost() {
		return dbhost;
	}
	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}
	public String getDbport() {
		return dbport;
	}
	public void setDbport(String dbport) {
		this.dbport = dbport;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
