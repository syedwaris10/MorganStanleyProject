package com.ms.project.dao;

import java.util.List;

import com.ms.project.dto.PartsRequestDto;

public interface PartsDaoLocal {

	public void savePartsData(PartsRequestDto parts);

	public void updateStaus(PartsRequestDto dto);

	public void submitItems(int requestId);

	public void deleteRejectedItems(int requestId);

	public void updateItemStateAndStatus(PartsRequestDto dto);

	public List<PartsRequestDto> getRequestUploadedData(String itemState, int requestId);
}