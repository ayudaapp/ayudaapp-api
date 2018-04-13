package com.ayuda.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ayuda.rest.domain.Chore;

public class ChoreMapper implements RowMapper<Chore>
{
	public Chore mapRow(ResultSet rs, int rowNum) throws SQLException {
		Chore chore = new Chore();
		chore.setId(rs.getInt("ID"));
		chore.setCustomerId(rs.getString("CUSTID"));
		chore.setChoreHeler(rs.getString("CHRHLPR"));
		chore.setConsent(rs.getString("CONSENT"));
		chore.setDate(rs.getString("CHOREDATE"));
		chore.setTime(rs.getString("CHORETIME"));
		chore.setStatus(rs.getInt("CHORESTATUS"));
		chore.setType(rs.getString("CHORETYPE"));
		chore.setPrice(rs.getDouble("CHOREPRICE"));
		
		return chore;
	}

}