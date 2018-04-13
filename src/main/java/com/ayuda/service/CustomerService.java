package com.ayuda.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ayuda.application.ServiceProperties;
import com.ayuda.dao.mapper.CustomerMapper;
import com.ayuda.rest.domain.Customer;



/*
 * Chore service to create/update/authenticate cuustomer
 */
@Service
//@Component
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;
    
    @Autowired
    private ServiceProperties serviceProps;

    public CustomerService() {
    	
    }

    public Customer createCustomer(Customer customer) {
               
        JdbcTemplate insert = new JdbcTemplate(getDataSource());        
        insert.update("INSERT INTO CUSTOMER (FNAME, LNAME, PHONE, EMAIL, ZIPCODE, COMMUNITYNAME, USERID, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[] { customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getEmail() , customer.getZipCode(), customer.getCommunityName(), customer.getUserId(), customer.getPassword()});
            
    	return customer;
    }

    public ServiceProperties getServiceProps() {
		return serviceProps;
	}

	public void setServiceProps(ServiceProperties serviceProps) {
		
		this.serviceProps = serviceProps;
	}

	
    public boolean authenticate(Customer cust) {
        
    	 JdbcTemplate selectTemplate = new JdbcTemplate(getDataSource()); 
    	 String selectQuery = "SELECT CUSTID, FNAME, LNAME, PHONE, EMAIL, ZIPCODE, COMMUNITYNAME, PASSWORD, USERID, RATING, DOB FROM CUSTOMER WHERE USERID=?";      
    	
    	  Customer customer = (Customer)selectTemplate.queryForObject(selectQuery, new Object[]{cust.getUserId()}, new CustomerMapper());
    	  
    	  if (cust.getPassword()!=null && cust.getPassword().equals(customer.getPassword())){
    		  return true;
    	  }else{
    		  return false;
    	  }
    		  
    }

    
    public int updateCustomer(Customer customer) {
        
   	 JdbcTemplate updateTemplate = new JdbcTemplate(getDataSource()); 
   	 
   	final ArrayList<Object> updateValueList = new ArrayList<Object>();
	final StringBuffer updateCols = new StringBuffer("UPDATE CUSTOMER SET");

	if (StringUtils.hasText(customer.getPhone())) {
		updateCols.append(" PHONE = ?,");
		updateValueList.add(customer.getPhone());
	}

	if (StringUtils.hasText(customer.getEmail())){
		updateCols.append(" EMAIL = ?,");
		updateValueList.add(customer.getEmail());
	}
	
	if (StringUtils.hasText(customer.getZipCode())){
		updateCols.append(" ZIPCODE = ?,");
		updateValueList.add(customer.getZipCode());
	}
	if (StringUtils.hasText(customer.getCommunityName())){
		updateCols.append(" COMMUNITYNAME = ?,");
		updateValueList.add(customer.getCommunityName());
	}
	if (StringUtils.hasText(customer.getPassword())){
		updateCols.append(" PASSWORD = ?,");
		updateValueList.add(customer.getPassword());
	}
	if (StringUtils.hasText(customer.getUserId())){
		updateCols.append(" USERID = ?,");
		updateValueList.add(customer.getUserId());
	}
	if (customer.getRating()!=null){
		updateCols.append(" RATING = ?,");
		updateValueList.add(customer.getRating());
	}
	if (StringUtils.hasText(customer.getTotalChores())){
		updateCols.append(" TOTALCHORES = ? ");
		updateValueList.add(customer.getTotalChores());
	}
	
	updateCols.append(" WHERE CUSTID=?");
	updateValueList.add(customer.getCustId());
	
	System.out.println(" Update SQL:"+updateCols.toString());
	System.out.println(updateValueList.toString());
   	// String sqlUpdate = "UPDATE CUSTOMER SET  PHONE=?, EMAIL=?, ZIPCODE=?, COMMUNITYNAME=?, PASSWORD=?, USERID=?, RATING=?, TOTALCHORES=? WHERE CUSTID=?";      
   	// int rows = updateTemplate.update(sqlUpdate, new Object[] {  customer.getPhone(), customer.getEmail(), customer.getZipCode(), customer.getCommunityName(), customer.getPassword(),customer.getUserId(), customer.getRating(), customer.getTotalChores(),customer.getCustId()});
	int rows = updateTemplate.update(updateCols.toString(),  updateValueList.toArray());
    return rows;
   }
    
      
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(serviceProps.getDriverClassName());
        dataSource.setUrl("jdbc:mysql://"+serviceProps.getDbhost()+":"+serviceProps.getDbport()+"/db_ayudha");
        dataSource.setUsername(serviceProps.getUsername());
        dataSource.setPassword(serviceProps.getPassword());
 
        return dataSource;
    }
    
    private Date getDate(String strDate){
    	Date date= new Date();
    	
    	try{
    	SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");
    	date = formatter.parse(strDate);
    	}catch(Exception ex){
    		
    	}
    	return date;
    }
    
    
}
