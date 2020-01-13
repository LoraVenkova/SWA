package de.hse.swa.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import de.hse.swa.dao.LicenseDao;
import de.hse.swa.dbmodel.License;


public class LicensesResource {

	
	  // Allows to insert contextual objects into the class,
	  // e.g. ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;

	  // Return the list of users to the user in the browser
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public List<License> getTusersBrowser() {
	    return LicenseDao.getInstance().getLicenses();
	  }

	  // Return the list of users in JSON format
	  @GET
	  @Produces({MediaType.APPLICATION_JSON })
	  public List<License> getUsers() {
		   return LicenseDao.getInstance().getLicenses();
	  }

	  // returns the number of users
	  // Use http://localhost:8080/Jodel/rest/users/count
	  // to get the total number of records
	  @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getCount() {
	    int count = LicenseDao.getInstance().getLicenses().size();
	    return String.valueOf(count);
	  }
	  
	  @POST
	  @Path("login/facebook")
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Map<String, Object> facebookLogin(License license, @Context HttpServletResponse servletResponse) {
		  License checkLicense = LicenseDao.getInstance().getLicensesByLicenseId(license.getLicenseID());
		  if(checkLicense==null) {
			  LicenseDao.getInstance().saveLicense(license);
			  checkLicense = LicenseDao.getInstance().getLicensesByLicenseId(license.getLicenseID());
		  }
		  Map<String, Object> response = new HashMap<>();
		  response.put("success", true);
		  response.put("license", checkLicense);
		  return response;
	  }

	  // This is the workhorse
	  @POST
	  @Produces(MediaType.APPLICATION_JSON)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public List<License> newLicense(License license,
			  @Context HttpServletResponse servletResponse) throws IOException {
	    LicenseDao.getInstance().saveLicense(license);
	    return LicenseDao.getInstance().getLicenses();
	  }

	  // Defines that the next path parameter after users is
	  // treated as a parameter and passed to the UserResources
	  // Allows to type http://localhost:8080/Rest/rest/users/1
	  // 1 will be treaded as parameter and passed to UserResource
	  @Path("{license}")
	  public LicenseResource getUser(@PathParam("license") Integer id) {
	    return new LicenseResource(uriInfo, request, id);
	  }

	} 
