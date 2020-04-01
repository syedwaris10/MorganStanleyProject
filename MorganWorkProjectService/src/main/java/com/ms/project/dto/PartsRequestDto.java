package com.ms.project.dto;

/**
 * @author Syed.Waris
 *
 */
public class PartsRequestDto {

	private String partNumber;
	private int partsQuantity;
	private String partName;
	private String priceUnit;
	private double pricePerUnit;
	private String itemState;
	private String status;
	private int requestId;
	
	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}
	/**
	 * @param partNumber the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	/**
	 * @return the partsQuantity
	 */
	public int getPartsQuantity() {
		return partsQuantity;
	}
	/**
	 * @param partsQuantity the partsQuantity to set
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
	 * @param partName the partName to set
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
	 * @param priceUnit the priceUnit to set
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
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	/**
	 * @return the itemState
	 */
	public String getItemState() {
		return itemState;
	}
	/**
	 * @param itemState the itemState to set
	 */
	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
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
	
	
}
