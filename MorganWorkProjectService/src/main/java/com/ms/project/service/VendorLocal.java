package com.ms.project.service;

import com.ms.project.dto.MessageDto;
import com.ms.project.dto.VendorDto;

public interface VendorLocal {

	public MessageDto createVendor(VendorDto dto);

	public MessageDto getBestDealsByVendors(int requestId);
}
