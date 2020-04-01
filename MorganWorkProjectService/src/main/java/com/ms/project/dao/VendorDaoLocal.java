package com.ms.project.dao;

import java.util.List;

import com.ms.project.dto.VendorDto;

public interface VendorDaoLocal {

	public void saveVendors(VendorDto dto);

	public List<VendorDto> getTopThreeBestDealsByVendor(int requestId);
}
