package de.hse.swa.dbmodel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the productversion database table.
 * 
 */
@Entity
@NamedQuery(name="Productversion.findAll", query="SELECT p FROM Productversion p")
public class Productversion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productVersionID;

	//bi-directional many-to-one association to License
	@OneToMany(mappedBy="productversion")
	private List<License> licenses;

	public Productversion() {
	}

	public int getProductVersionID() {
		return this.productVersionID;
	}

	public void setProductVersionID(int productVersionID) {
		this.productVersionID = productVersionID;
	}

	public List<License> getLicenses() {
		return this.licenses;
	}

	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}

	public License addLicens(License licens) {
		getLicenses().add(licens);
		licens.setProductversion(this);

		return licens;
	}

	public License removeLicens(License licens) {
		getLicenses().remove(licens);
		licens.setProductversion(null);

		return licens;
	}

}