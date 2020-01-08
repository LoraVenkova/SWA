package de.hse.swa.dbmodel;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;

	@Column(name="CURRENT_CONNECTIONS")
	private BigInteger currentConnections;

	private String emailAddress;

	private byte isSystemAdmin;

	private String name;

	private byte password;

	@Column(name="TOTAL_CONNECTIONS")
	private BigInteger totalConnections;

	private String user;

	private String username;

	private String userscol;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public BigInteger getCurrentConnections() {
		return this.currentConnections;
	}

	public void setCurrentConnections(BigInteger currentConnections) {
		this.currentConnections = currentConnections;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public byte getIsSystemAdmin() {
		return this.isSystemAdmin;
	}

	public void setIsSystemAdmin(byte isSystemAdmin) {
		this.isSystemAdmin = isSystemAdmin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getPassword() {
		return this.password;
	}

	public void setPassword(byte password) {
		this.password = password;
	}

	public BigInteger getTotalConnections() {
		return this.totalConnections;
	}

	public void setTotalConnections(BigInteger totalConnections) {
		this.totalConnections = totalConnections;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserscol() {
		return this.userscol;
	}

	public void setUserscol(String userscol) {
		this.userscol = userscol;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}