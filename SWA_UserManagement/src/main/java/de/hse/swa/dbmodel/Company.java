package de.hse.swa.dbmodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int companyID;

	private String address;

	private String department;

	private String name;

	//bi-directional many-to-one association to Servicecontract
	@OneToMany(mappedBy="company")
	private List<Servicecontract> servicecontracts;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="company")
	private List<User> users;

	public Company() {
	}

	public int getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Servicecontract> getServicecontracts() {
		return this.servicecontracts;
	}

	public void setServicecontracts(List<Servicecontract> servicecontracts) {
		this.servicecontracts = servicecontracts;
	}

	public Servicecontract addServicecontract(Servicecontract servicecontract) {
		getServicecontracts().add(servicecontract);
		servicecontract.setCompany(this);

		return servicecontract;
	}

	public Servicecontract removeServicecontract(Servicecontract servicecontract) {
		getServicecontracts().remove(servicecontract);
		servicecontract.setCompany(null);

		return servicecontract;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setCompany(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setCompany(null);

		return user;
	}

}