package com.ayuda.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayuda.rest.domain.Chore;
import com.ayuda.service.ChoresService;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/ayuda/v1/chores")
@Api(tags = {"chores"})
public class ChoresController extends AbstractRestHandler {

    
	 @Autowired    
	 private ChoresService choresService;

	 
	 
    @RequestMapping(value = "", method = RequestMethod.POST,  consumes = {"application/json", "application/xml"},  produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a chore .", notes = "Creates a chore and push the notification to chore helpers.")
    public @ResponseBody Chore createChore(@RequestBody Chore chore,
                                HttpServletRequest request, HttpServletResponse response) {
        
    	Chore choreCreated = this.choresService.createChore(chore);    	
        return choreCreated;    	
    }

    
   /* @RequestMapping(value = "/acceptChore", method = RequestMethod.PUT, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Accepts a chore .", notes = "Accepts the Chore and sends notification to chore creator.")
    public void acceptChore(@RequestBody Chore chore,  HttpServletRequest request, HttpServletResponse response) {
       
    	int rows = this.choresService.acceptChore(chore);
        response.setHeader("Location", request.getRequestURL().append("/").append(rows).toString());
    }*/
    
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update a chore .", notes = "Update chore.")
    public void updateChore(@RequestBody Chore chore,  HttpServletRequest request, HttpServletResponse response) {
       
    	int rows = this.choresService.updateChore(chore);
        response.setHeader("Location", request.getRequestURL().append("/").append(rows).toString());
    }
    
    
    @RequestMapping(value = "/{id}",  method = RequestMethod.GET,  produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Chore.", notes = "You have to provide a valid chore ID.")
    public
    @ResponseBody Chore getHotel(@ApiParam(value = "The ID of the Chore.", required = true) @PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Chore chore = this.choresService.getChore(id);       
        return chore;
    }
    
   /* @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Hotel> getAllHotel(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.hotelService.getAllHotels(page, size);
    }*/
    
}
