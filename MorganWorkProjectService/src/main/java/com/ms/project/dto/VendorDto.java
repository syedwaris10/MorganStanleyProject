package com.ms.project.dto;

public class VendorDto {

	private int requestId;
	private String vendorId;
	private String vendorName;
	private double quotaionValue;
	private int timeFrame;
	private double score;
	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	/**
	 * @return the vendorId
	 */
	public String getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
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
	 * @param quotaionValue the quotaionValue to set
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
	 * @param timeFrame the timeFrame to set
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
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
	
}
