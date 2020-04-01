/**
 * 
 */
package com.ms.project.dto;

/**
 * @author Syed.Waris
 *
 */
public class HardwareDataDto {

	private LoggedinUserDto loggedInUserDto ;
	private int requestId;
	/**
	 * @return the loggedInUserDto
	 */
	public LoggedinUserDto getLoggedInUserDto() {
		return loggedInUserDto;
	}
	/**
	 * @param loggedInUserDto the loggedInUserDto to set
	 */
	public void setLoggedInUserDto(LoggedinUserDto loggedInUserDto) {
		this.loggedInUserDto = loggedInUserDto;
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
