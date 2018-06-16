package com.ayuda.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ayuda.application.ServiceProperties;
import com.ayuda.dao.jpa.ChoresRepository;
import com.ayuda.dao.mapper.ChoreMapper;
import com.ayuda.rest.domain.Chore;
import com.ayuda.rest.domain.ChoreCategory;


/*
 * Chore service to create/update/accept chores
 */
@Service
//@Component
public class ChoresService {

    private static final Logger log = LoggerFactory.getLogger(ChoresService.class);

    //@Autowired(required=true)  
    private ChoresRepository choresRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;
    
    @Autowired
    private ServiceProperties serviceProps;

    public ChoresService() {
    	
    }

    public Chore createChore(Chore chore) {
               
        System.out.println("===========Price:"+chore.getPrice());
    	JdbcTemplate insert = new JdbcTemplate(getDataSource());        
       /* insert.update("INSERT INTO CHORES (CUSTID, DESCRIPTION, CONSENT, CHORESTATUS, CHORETYPE, CHOREPRICE, CHOREDATE) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new Object[] { chore.getChoreCreator(), chore.getDescription(), chore.getConsent(), 1 , chore.getType(), chore.getPrice(), chore.getDate()});*/
    	 insert.update("INSERT INTO CHORES (CUSTID, DESCRIPTION, CONSENT, CHORESTATUS, CHORETYPE, CHOREPRICE) VALUES (?, ?, ?, ?, ?, ?)",
                 new Object[] { chore.getChoreCreator(), chore.getDescription(), chore.getConsent(), 1 , chore.getType(), chore.getPrice()});
            
    	return chore;
    }

    public ServiceProperties getServiceProps() {
		return serviceProps;
	}

	public void setServiceProps(ServiceProperties serviceProps) {
		
		this.serviceProps = serviceProps;
	}

	
    public int updateChore(Chore chore) {
        
    	 JdbcTemplate updateTemplate = new JdbcTemplate(getDataSource()); 
    	 
    	 final ArrayList<Object> updateValueList = new ArrayList<Object>();
    		final StringBuffer updateCols = new StringBuffer("UPDATE CHORES SET");

    		if (StringUtils.hasText(chore.getDescription())) {
    			updateCols.append(" DESCRIPTION = ?,");
    			updateValueList.add(chore.getDescription());
    		}
    		
    		if (StringUtils.hasText(chore.getDate())){
    			updateCols.append(" CHOREDATE = ?,");
    			updateValueList.add(chore.getDate());
    		}

    		if (StringUtils.hasText(chore.getTime())){
    			updateCols.append(" CHORETIME = ?,");
    			updateValueList.add(chore.getTime());
    		}
    		
    		if (chore.getPrice()>0){
    			updateCols.append(" CHOREPRICE = ?,");
    			updateValueList.add(chore.getPrice());
    		}
    		if (chore.getStatus()!=0){
    			updateCols.append(" CHORESTATUS = ?,");
    			updateValueList.add(chore.getStatus());
    		}
    		if (StringUtils.hasText(chore.getType())){
    			updateCols.append(" CHORETYPE = ?");
    			updateValueList.add(chore.getType());
    		}
    		    		
    		updateCols.append(" WHERE ID=?");
    		updateValueList.add(chore.getId());

    	 //String sqlUpdate = "UPDATE CHORES SET  DESCRIPTION=?, CHOREDATE=?, CHORETIME=?, PRICE=?, CHORESTATUS = ?, CHORETYPE=? WHERE ID=?";      
    	// int rows = updateTemplate.update(sqlUpdate, new Object[] {  chore.getDescription(), getDate(chore.getDate()), chore.getTime() , chore.getPrice(), chore.getId()});
    		int rows = updateTemplate.update(updateCols.toString(), updateValueList.toArray());
         return rows;
    }

    
    public int acceptChore(Chore chore) {
        
   	 JdbcTemplate updateTemplate = new JdbcTemplate(getDataSource()); 
   	 String sqlUpdate = "UPDATE CHORES SET  CHOREHLPR=?, CHORESTATUS=? WHERE ID=?";      
   	 int rows = updateTemplate.update(sqlUpdate, new Object[] {  chore.getChoreHeler(), chore.getStatus(), chore.getId()});
        return rows;
   }
    
    
    public void deleteChore(Long id) {
        choresRepository.delete(id);
    }

    
    public Chore getChore(long choreId){
    	
    	JdbcTemplate selectTemplate = new JdbcTemplate(getDataSource()); 
    
    	String selectQuery = "SELECT ID, CUSTID, DESCRIPTION, CHRHLPR, REPEATCHORE, CONSENT, CHOREDATE, CHORETIME, CHORESTATUS, CHORETYPE, CHOREPRICE FROM CHORES WHERE ID=?";
    	Chore chore = (Chore)selectTemplate.queryForObject(selectQuery, new Object[]{choreId}, new ChoreMapper());
    	
    	return chore;
    }
    
    public ChoreCategory[] getChoreCategories() {
    	
    	JdbcTemplate selectTemplate = new JdbcTemplate(getDataSource());    	
    	String selectQuery = "SELECT CHORECAT, CHORECATDES FROM CHORECATEGORIES ORDER BY SORTORDER";  
    	
    	   	
    	List<ChoreCategory> choreCategoriesList = selectTemplate.query(selectQuery,new RowMapper<ChoreCategory>(){  
    	    @Override  
    	    public ChoreCategory mapRow(ResultSet rs, int rownumber) throws SQLException {  
    	    	ChoreCategory choreCategory=new ChoreCategory();  
    	    	choreCategory.setChoreCategoryId(rs.getString("CHORECAT"));
    	    	choreCategory.setChoreCategoryDes(rs.getString("CHORECATDES"));  
    	        
    	        return choreCategory;  
    	    }  
    	    });  
    	 
    	ChoreCategory[] choreCategories = new ChoreCategory[choreCategoriesList.size()];
    		
		return choreCategoriesList.toArray(choreCategories);
	}
    //http://goo.gl/7fxvVf
    public Page<Chore> getAllHotels(Integer page, Integer size) {
        Page pageOfHotels = choresRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        
        if (size > 50) {
            counterService.increment("ChoresService.getAll.largePayload");
           
        }
        return pageOfHotels;
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
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    	System.out.println("============strDate:"+strDate);
    	date = formatter.parse(strDate);
    	System.out.println("============date:"+strDate);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return date;
    }

	
    
    
}
