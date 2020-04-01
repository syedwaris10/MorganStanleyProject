/**
 * 
 */
package com.ms.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.project.constants.ItemState;
import com.ms.project.constants.Roles;
import com.ms.project.constants.Status;
import com.ms.project.dao.PartsDaoLocal;
import com.ms.project.dto.HardwareDataDto;
import com.ms.project.dto.LoggedinUserDto;
import com.ms.project.dto.MessageDto;
import com.ms.project.dto.PartsRequestDto;
import com.ms.project.exceptions.InvalidInputFault;
import com.ms.project.exceptions.NoResultFault;
import com.ms.project.util.CommonUtils;
import com.ms.project.util.ServiceUtil;

/**
 * @author Syed.Waris
 *
 */
@Service
public class PartsUpdate implements PartsUpdateLocal {

	@Autowired
	private PartsDaoLocal partsDaoLocal;

	/**
	 * this method is for uploading new parts.
	 * 
	 * @param partsList
	 */
	@Override
	public MessageDto createNewRequest(List<PartsRequestDto> partsList) {
		try {
			if (!ServiceUtil.isEmpty(partsList) && partsList.size() > 0) {
				int requestId = CommonUtils.getRandomNumber();
				for (PartsRequestDto partsDto : partsList) {
					if (!ServiceUtil.isEmpty(partsDto)) {
						partsDto.setRequestId(requestId);
						if (isPartNumberValid(partsDto.getPartNumber()) && isPartNameValid(partsDto.getPartName())) {
							partsDto.setItemState(ItemState.L1Approval.name());
							partsDto.setStatus(Status.Inprogress.name());
							partsDaoLocal.savePartsData(partsDto);
						} else {
							throw new InvalidInputFault("Please enter valid part number and part name.");
						}
					}
				}
			} else {
				throw new InvalidInputFault("Please enter valid hardware details.");
			}
		} catch (InvalidInputFault e) {
			CommonUtils.getFailedMessage(null, e.getMessage());
		}
		return CommonUtils.getSuccessMessage(null);
	}

	private boolean isPartNameValid(String partName) {
		String regex = "[aA-zZ]+";
		if (partName.matches(regex)) {
			return true;
		}
		return false;
	}

	/**
	 * validate part number
	 * 
	 * @param partNumber
	 * @return
	 */
	private boolean isPartNumberValid(String partNumber) {
		String regex = "[aA0-zZ9]{10}";
		if (partNumber.matches(regex)) {
			return true;
		}
		return false;
	}

	/**
	 * this method updates the status of item to approved or rejected.
	 */
	@Override
	public MessageDto updateStatus(List<PartsRequestDto> dtolist) {
		if (!ServiceUtil.isEmpty(dtolist)) {
			try {
				for (PartsRequestDto dto : dtolist) {
					String status = dto.getStatus();
					String partNumber = dto.getPartNumber();
					validateStatus(status);
					if (isPartNumberValid(partNumber) && dto.getRequestId() > 0) {
						partsDaoLocal.updateStaus(dto);
					} else {
						throw new InvalidInputFault("Please enter valid requestId and Part number.");
					}
				}
			} catch (InvalidInputFault e) {
				return CommonUtils.getFailedMessage(null, e.getMessage());
			}
		}
		return CommonUtils.getSuccessMessage(null);
	}

	private void setItemState(PartsRequestDto dto) throws InvalidInputFault {
		String itemState = dto.getItemState();
		String status = dto.getStatus();
		if (!ServiceUtil.isEmpty(itemState) && !ServiceUtil.isEmpty(status)) {
			if (itemState.equals(ItemState.L1Approval.name()) && status.equals(Status.Approved.name())) {
				dto.setItemState(ItemState.L2Approval.name());
				dto.setStatus(Status.Inprogress.name());
			} else if (itemState.equals(ItemState.L2Approval.name()) && status.equals(Status.Approved.name())) {
				dto.setItemState(ItemState.L2Approved.name());
			} else if (itemState.equals(ItemState.L3Approval.name()) && status.equals(Status.Approved.name())) {
				dto.setItemState(ItemState.L3Approved.name());
			}
		} else {
			throw new InvalidInputFault("Please enter valid state and status.");
		}

	}

	/**
	 * validates item status
	 * 
	 * @param status
	 * @throws InvalidInputFault
	 */
	private void validateStatus(String status) throws InvalidInputFault {
		if (!status.equalsIgnoreCase(Status.Approved.name()) || !status.equalsIgnoreCase(Status.Rejected.name())) {
			throw new InvalidInputFault("Please enter valid Status.");
		}

	}

	/**
	 * this method is for final submission of parts after evaluation by
	 * Approvers
	 * 
	 * @param partsList
	 * @return MessageDto
	 */
	@Override
	public MessageDto submitParts(List<PartsRequestDto> partsList) {
		try {
			if (!ServiceUtil.isEmpty(partsList)) {
				PartsRequestDto dto = partsList.get(0);
				if (!ServiceUtil.isEmpty(dto)) {
					int requestId = dto.getRequestId();
					partsDaoLocal.deleteRejectedItems(requestId);
					setItemState(dto);
					partsDaoLocal.updateItemStateAndStatus(dto);
				}
			} else {

				throw new InvalidInputFault("Invalid Parts details!");
			}
		} catch (InvalidInputFault e) {
			return CommonUtils.getFailedMessage(null, e.getMessage());
		}

		return CommonUtils.getSuccessMessage(null);
	}

	/**
	 * to show on vendor screen
	 */
	@Override
	public MessageDto getHardwareDetailsData(HardwareDataDto hardwareData) {
		List<PartsRequestDto> list = null;
		try {
			if (!ServiceUtil.isEmpty(hardwareData)) {
				LoggedinUserDto loggedInUserDto = hardwareData.getLoggedInUserDto();
				if (!ServiceUtil.isEmpty(loggedInUserDto)) {
					int requestId = hardwareData.getRequestId();
					String roles = loggedInUserDto.getRoles();
					String emailId = loggedInUserDto.getEmailid();
					if (!ServiceUtil.isEmpty(roles) && !ServiceUtil.isEmpty(emailId)) {
						String itemState = getItemState(roles);
						list = partsDaoLocal.getRequestUploadedData(itemState, requestId);
						if (ServiceUtil.isEmpty(list)) {
							throw new NoResultFault("No records found!");
						}
					} else {
						throw new InvalidInputFault("Inalid credentials!");
					}
				} else {
					throw new InvalidInputFault("Inalid Input!");
				}

			} else {
				throw new InvalidInputFault("Inalid Input!");
			}
		} catch (InvalidInputFault | NoResultFault e) {
			return CommonUtils.getFailedMessage(null, e.getMessage());
		}

		return CommonUtils.getSuccessMessage(list);
	}

	private String getItemState(String roles) {
		String itemState = "";
		if (roles.equals(Roles.L1Appoval.name())) {
			itemState = ItemState.L1Approval.name();
		} else if (roles.equals(Roles.L2Approval.name())) {
			itemState = ItemState.L2Approval.name();
		} else if (roles.equals(Roles.L3Approval.name())) {
			itemState = ItemState.L3Approval.name();
		} else {
			// for vendor screen
			itemState = ItemState.L2Approved.name();
		}
		return itemState;
	}

}
