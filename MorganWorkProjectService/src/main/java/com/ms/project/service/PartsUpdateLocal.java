package com.ms.project.service;

import java.util.List;

import com.ms.project.dto.HardwareDataDto;
import com.ms.project.dto.MessageDto;
import com.ms.project.dto.PartsRequestDto;

public interface PartsUpdateLocal {

	public MessageDto createNewRequest(List<PartsRequestDto> partsList);

	public MessageDto updateStatus(List<PartsRequestDto> dto);

	public MessageDto submitParts(List<PartsRequestDto> partsList);

	public MessageDto getHardwareDetailsData(HardwareDataDto hardwareData);

}
