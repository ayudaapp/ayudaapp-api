package com.ayuda.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ayuda.application.ServiceProperties;



/*
 * Sample service to demonstrate what the API would use to get things done
 */
//@Service
@Component
public class BasicService {

    private static final Logger log = LoggerFactory.getLogger(BasicService.class);
    private static BasicService instance = new BasicService();
   
    private ServiceProperties serviceProps; 
      
    protected BasicService() {
    	 try {
             //Step 2: Load MySQL Java driver
             Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
    }
       
    private Connection createConnection() {
    	 
        Connection connection = null;
        try {
            //Step 3: Establish Java MySQL connection
        	
        	System.out.println("YML:"+serviceProps.getDriverClassName());
            System.out.println("YML:"+serviceProps.getDbhost());
            System.out.println("YML:"+serviceProps.getDbport());
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ayudha", "root", "");
            
            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.println(dbmd.getDatabaseProductName());
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
         
            
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
   
	public ServiceProperties getServiceProps() {
		return serviceProps;
	}

	public void setServiceProps(ServiceProperties serviceProps) {
		this.serviceProps = serviceProps;
	}

	public static Connection getConnection() {
        return instance.createConnection();
    }
    }
