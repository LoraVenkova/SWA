package de.hse.swa.dbmodel;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the license database table.
 * 
 */
@Entity
@NamedQuery(name="License.findAll", query="SELECT l FROM License l")
public class License implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int licenseID;

	private String expirationDate;

	private String ipNumber;

	private String licenseCount;

	private String licenseKey;

	//bi-directional many-to-one association to Servicecontract
	@ManyToOne
	private Servicecontract servicecontract;

	public License() {
	}

	public int getLicenseID() {
		return this.licenseID;
	}

	public void setLicenseID(int licenseID) {
		this.licenseID = licenseID;
	}

	public String getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIpNumber() {
		return this.ipNumber;
	}

	public void setIpNumber(String ipNumber) {
		this.ipNumber = ipNumber;
	}

	public String getLicenseCount() {
		return this.licenseCount;
	}

	public void setLicenseCount(String licenseCount) {
		this.licenseCount = licenseCount;
	}

	public String getLicenseKey() {
		return this.licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public Servicecontract getServicecontract() {
		return this.servicecontract;
	}

	public void setServicecontract(Servicecontract servicecontract) {
		this.servicecontract = servicecontract;
	}

}