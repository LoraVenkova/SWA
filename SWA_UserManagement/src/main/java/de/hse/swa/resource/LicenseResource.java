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
import de.hse.swa.dao.LicenseDao;
import de.hse.swa.dbmodel.License;

public class LicenseResource  {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public LicenseResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public License getLicense() {
		License license = LicenseDao.getInstance().getLicense(id);
		if(license==null)
			throw new RuntimeException("Get: License with " + id +  " not found");
		return license;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public License getTuserHTML() {
		License license = LicenseDao.getInstance().getLicense(id);
		if(license==null)
			throw new RuntimeException("Get: License with " + id +  " not found");
		return license;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putLicense(JAXBElement<License> license) {
		License l = license.getValue();
		return putAndGetResponse(l);
	}

	@DELETE
	public void deleteLicense() {
		LicenseDao.getInstance().deleteLicense(id);
	}

	private Response putAndGetResponse(License license) {
		Response res;
		LicenseDao.getInstance().saveLicense(license);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
} 