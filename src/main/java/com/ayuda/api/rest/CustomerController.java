package com.ayuda.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayuda.rest.domain.Customer;
import com.ayuda.service.CustomerService;
import com.ayuda.service.exception.ResourceNotFoundException;
import com.ayuda.service.exception.RestErrorInfo;

/*
 * Chore service to create/update/accept customer
 */
@RestController
@RequestMapping(value = "/ayuda/v1/customer")
@Api(tags = {"customer"})
public class CustomerController {

	
	@Autowired    
	private CustomerService customerService;

	 
   @RequestMapping(value = "", method = RequestMethod.POST,  consumes = {"application/json", "application/xml"},  produces = {"application/json", "application/xml"})
   @ResponseStatus(HttpStatus.CREATED)
   @ApiOperation(value = "Creates a customer.", notes = "Creates a new customer.")
   public @ResponseBody Customer createChore(@RequestBody Customer customer,
                               HttpServletRequest request, HttpServletResponse response) {
       
   	   Customer cust = this.customerService.createCustomer(customer); 	
       return cust;    	
   }
   
   @RequestMapping(value = "/authenticate", method = RequestMethod.POST,  consumes = {"application/json", "application/xml"},  produces = {"application/json", "application/xml"})
   @ResponseStatus(HttpStatus.CREATED)
   @ApiOperation(value = "Authenticates the customer.", notes = "Authenticates the customer.")
   public @ResponseBody Boolean authenticate(@RequestBody Customer customer,
                               HttpServletRequest request, HttpServletResponse response) {
       
   	   Boolean authenticate = this.customerService.authenticate(customer);
       return authenticate;    	
   }

   @RequestMapping(value = "", method = RequestMethod.PUT,  consumes = {"application/json", "application/xml"},  produces = {"application/json", "application/xml"})
   @ResponseStatus(HttpStatus.CREATED)
   @ApiOperation(value = "Update Customer.", notes = "Update Customer.")
   public @ResponseBody int updateCustomer(@RequestBody Customer customer,
                               HttpServletRequest request, HttpServletResponse response) {
       if (customer.getCustId()==0){
    	   ResourceNotFoundException ex = new ResourceNotFoundException("Customer is null");
    	     throw ex;
       }
   	   int rows = this.customerService.updateCustomer(customer);
       return rows;    	
   }
}
