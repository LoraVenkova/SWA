package de.hse.swa.dbmodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the systemadministrator database table.
 * 
 */
@Entity
@NamedQuery(name="Systemadministrator.findAll", query="SELECT s FROM Systemadministrator s")
public class Systemadministrator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int systemAdministratorID;

	private String emailAddress;

	private String firstName;

	private String lastName;

	private String loginName;

	private String password;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="systemadministrator")
	private List<User> users;

	public Systemadministrator() {
	}

	public int getSystemAdministratorID() {
		return this.systemAdministratorID;
	}

	public void setSystemAdministratorID(int systemAdministratorID) {
		this.systemAdministratorID = systemAdministratorID;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setSystemadministrator(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setSystemadministrator(null);

		return user;
	}

}