package de.hse.swa.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import javax.xml.bind.JAXBElement;

import de.hse.swa.dao.ServicecontractDao;
import de.hse.swa.dbmodel.Servicecontract;;

public class ServiceContractResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public ServiceContractResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Servicecontract getServicecontract() {
		Servicecontract serviceContract = ServicecontractDao.getInstance().getServicecontract(id);
		if(serviceContract==null)
			throw new RuntimeException("Get: Service Contract with " + id +  " not found");
		return serviceContract;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Servicecontract getTuserHTML() {
		Servicecontract serviceContract = ServicecontractDao.getInstance().getServicecontract(id);
		if(serviceContract==null)
			throw new RuntimeException("Get: Service Contract with " + id +  " not found");
		return serviceContract;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTuser(JAXBElement<Servicecontract> serviceContract) {
		Servicecontract s = serviceContract.getValue();
		return putAndGetResponse(s);
	}

	@DELETE
	public void deleteServicecontract() {
		ServicecontractDao.getInstance().deleteServicecontract(id);
	}

	private Response putAndGetResponse(Servicecontract serviceContract) {
		Response res;
	    ServicecontractDao.getInstance().saveServicecontract(serviceContract);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
} 