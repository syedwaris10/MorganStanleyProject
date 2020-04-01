/**
 * 
 */
package com.ms.project.dto;

import java.util.List;

/**
 * @author Syed.Waris
 *
 */
public class PartsDto {

	private List<PartsRequestDto> list;
	private int requestId;
	/**
	 * @return the list
	 */
	public List<PartsRequestDto> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<PartsRequestDto> list) {
		this.list = list;
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
