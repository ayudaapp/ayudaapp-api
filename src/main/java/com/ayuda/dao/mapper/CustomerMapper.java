package com.ayuda.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ayuda.rest.domain.Customer;

public class CustomerMapper implements RowMapper<Customer>
{
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("CUSTID"));
		customer.setFirstName(rs.getString("FNAME"));
		customer.setLastName(rs.getString("LNAME"));
		customer.setPhone(rs.getString("PHONE"));
		customer.setEmail(rs.getString("EMAIL"));
		customer.setUserId(rs.getString("USERID"));
		customer.setPassword(rs.getString("PASSWORD"));
		customer.setCommunityName(rs.getString("COMMUNITYNAME"));
		customer.setRating(rs.getDouble("RATING"));
		customer.setRating(rs.getDouble("ZIPCODE"));
		customer.setDob(rs.getDate("DOB"));
		return customer;
	}

}