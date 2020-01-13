package de.hse.swa.resource;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import de.hse.swa.dao.UserDao;
import de.hse.swa.dbmodel.User;

// Will map the resource to the URL users
@Path("/users")
public class UsersResource {

	
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of users to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<User> getTusersBrowser() {
    return UserDao.getInstance().getUsers();
  }

  // Return the list of users in JSON format
  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public List<User> getUsers() {
	   return UserDao.getInstance().getUsers();
  }

  // returns the number of users
  // Use http://localhost:8080/Jodel/rest/users/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = UserDao.getInstance().getUsers().size();
    return String.valueOf(count);
  }
  
  @POST
  @Path("login/facebook")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Map<String, Object> facebookLogin(User user, @Context HttpServletResponse servletResponse) {
	  User checkUser = UserDao.getInstance().getUsersByUserId(user.getUserID());
	  if(checkUser==null) {
		  UserDao.getInstance().saveUser(user);
		  checkUser = UserDao.getInstance().getUsersByUserId(user.getUserID());
	  }
	  Map<String, Object> response = new HashMap<>();
	  response.put("success", true);
	  response.put("user", checkUser);
	  return response;
  }

  // This is the workhorse
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<User> newUser(User user,
		  @Context HttpServletResponse servletResponse) throws IOException {
    UserDao.getInstance().saveUser(user);
    return UserDao.getInstance().getUsers();
  }

  // Defines that the next path parameter after users is
  // treated as a parameter and passed to the UserResources
  // Allows to type http://localhost:8080/Rest/rest/users/1
  // 1 will be treaded as parameter and passed to UserResource
  @Path("{user}")
  public UserResource getUser(@PathParam("user") Integer id) {
    return new UserResource(uriInfo, request, id);
  }

} 
