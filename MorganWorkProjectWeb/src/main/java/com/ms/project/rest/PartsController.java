package com.ms.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.project.dto.HardwareDataDto;
import com.ms.project.dto.MessageDto;
import com.ms.project.dto.PartsRequestDto;
import com.ms.project.service.PartsUpdateLocal;

@RestController
@RequestMapping(value = "/hardwareParts", produces = "application/json")
public class PartsController {
	@Autowired
	private PartsUpdateLocal partsUpdateLocal;

	@RequestMapping(value = "/uploadParts", method = RequestMethod.POST)
	public MessageDto createNewRequest(List<PartsRequestDto> partsList) {
		return partsUpdateLocal.createNewRequest(partsList);
	}

	@RequestMapping(value = "/updateItemStatus", method = RequestMethod.POST)
	public MessageDto updateStatus(List<PartsRequestDto> dtolist) {
		return partsUpdateLocal.updateStatus(dtolist);
	}

	@RequestMapping(value = "/submitData", method = RequestMethod.POST)
	public MessageDto submitParts(List<PartsRequestDto> partsList) {
		return partsUpdateLocal.submitParts(partsList);
	}

	@RequestMapping(value = "/getHardwareData", method = RequestMethod.POST)
	public MessageDto getHardwareDetailsData(HardwareDataDto hardwareData) {
		return partsUpdateLocal.getHardwareDetailsData(hardwareData);
	}
}
