package com.ms.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Vendor")

public class VendorDo implements Serializable{

	@EmbeddedId
	private VendorDoPk pk;

	@Column(name = "Vendor_Name")
	private String vendorName;

	@Column(name = "Quotation_Value")
	private double quotaionValue;

	@Column(name = "Time_Frame")
	private int timeFrame;

	@Column(name = "SCORE")
	private double score;

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName
	 *            the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the quotaionValue
	 */
	public double getQuotaionValue() {
		return quotaionValue;
	}

	/**
	 * @param quotaionValue
	 *            the quotaionValue to set
	 */
	public void setQuotaionValue(double quotaionValue) {
		this.quotaionValue = quotaionValue;
	}

	/**
	 * @return the timeFrame
	 */
	public int getTimeFrame() {
		return timeFrame;
	}

	/**
	 * @param timeFrame
	 *            the timeFrame to set
	 */
	public void setTimeFrame(int timeFrame) {
		this.timeFrame = timeFrame;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @return the pk
	 */
	public VendorDoPk getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(VendorDoPk pk) {
		this.pk = pk;
	}
}
