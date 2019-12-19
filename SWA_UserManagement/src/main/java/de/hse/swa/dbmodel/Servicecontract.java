package de.hse.swa.dbmodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the servicecontract database table.
 * 
 */
@Entity
@NamedQuery(name="Servicecontract.findAll", query="SELECT s FROM Servicecontract s")
public class Servicecontract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serviceContractID;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	//bi-directional many-to-one association to License
	@OneToMany(mappedBy="servicecontract")
	private List<License> licenses;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="servicecontract")
	private List<User> users;

	public Servicecontract() {
	}

	public int getServiceContractID() {
		return this.serviceContractID;
	}

	public void setServiceContractID(int serviceContractID) {
		this.serviceContractID = serviceContractID;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<License> getLicenses() {
		return this.licenses;
	}

	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}

	public License addLicens(License licens) {
		getLicenses().add(licens);
		licens.setServicecontract(this);

		return licens;
	}

	public License removeLicens(License licens) {
		getLicenses().remove(licens);
		licens.setServicecontract(null);

		return licens;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setServicecontract(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setServicecontract(null);

		return user;
	}

}