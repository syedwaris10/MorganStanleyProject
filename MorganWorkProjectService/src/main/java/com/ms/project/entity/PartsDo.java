package com.ms.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PartsTable")
public class PartsDo implements Serializable {

	@EmbeddedId
	private PartsDoPk pk;

	@Column(name = "Quantity")
	private int partsQuantity;

	@Column(name = "PartName", length = 50)
	private String partName;

	@Column(name = "UOM")
	private String priceUnit;

	@Column(name = "PricePerUnit")
	private double pricePerUnit;

	@Column(name = "Status", length = 30)
	private String status;

	/**
	 * @return the partsQuantity
	 */
	public int getPartsQuantity() {
		return partsQuantity;
	}

	/**
	 * @param partsQuantity
	 *            the partsQuantity to set
	 */
	public void setPartsQuantity(int partsQuantity) {
		this.partsQuantity = partsQuantity;
	}

	/**
	 * @return the partName
	 */
	public String getPartName() {
		return partName;
	}

	/**
	 * @param partName
	 *            the partName to set
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}

	/**
	 * @return the priceUnit
	 */
	public String getPriceUnit() {
		return priceUnit;
	}

	/**
	 * @param priceUnit
	 *            the priceUnit to set
	 */
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	/**
	 * @return the pricePerUnit
	 */
	public double getPricePerUnit() {
		return pricePerUnit;
	}

	/**
	 * @param pricePerUnit
	 *            the pricePerUnit to set
	 */
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the pk
	 */
	public PartsDoPk getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(PartsDoPk pk) {
		this.pk = pk;
	}
}
