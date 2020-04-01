package com.ms.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.project.dao.VendorDaoLocal;
import com.ms.project.dto.MessageDto;
import com.ms.project.dto.VendorDto;
import com.ms.project.exceptions.InvalidInputFault;
import com.ms.project.exceptions.NoResultFault;
import com.ms.project.util.CommonUtils;
import com.ms.project.util.ServiceUtil;

/**
 * 
 * @author Syed.Waris
 * @version 1.0
 * @since 30-03-2020
 *
 */
@Service
public class Vendor implements VendorLocal {

	@Autowired
	private VendorDaoLocal vendorDaoLocal;

	/**
	 * This creates vendor bids for that requestId.
	 * 
	 * @param dto
	 */
	@Override
	public MessageDto createVendor(VendorDto dto) {
		try {
			if (!ServiceUtil.isEmpty(dto)) {
				int deliveryTime = dto.getTimeFrame();
				if (!ServiceUtil.isEmpty(deliveryTime) && deliveryTime > 0) {
					double quotationValue = dto.getQuotaionValue();
					if (quotationValue > 0) {
						double weightedAverage = (dto.getQuotaionValue() * 0.6) + (dto.getTimeFrame() * 0.4);
						dto.setScore(weightedAverage);
						vendorDaoLocal.saveVendors(dto);
					} else {
						throw new InvalidInputFault("quotationValue cannot be less than or equal to zero.");
					}
				} else {
					throw new InvalidInputFault("Please enter correct delivery time.");
				}
			} else {
				throw new InvalidInputFault("Please enter correct details for vendor.");
			}
		} catch (InvalidInputFault e) {
			return CommonUtils.getFailedMessage(null, e.getMessage());
		}
		return CommonUtils.getSuccessMessage(null);
	}

	/**
	 * this method fetches best 3 deals by vendor
	 * 
	 * @param requestId
	 */
	@Override
	public MessageDto getBestDealsByVendors(int requestId) {
		List<VendorDto> list = null;
		try {
			if (requestId > 0) {
				list = vendorDaoLocal.getTopThreeBestDealsByVendor(requestId);
				if (ServiceUtil.isEmpty(list)) {
					throw new NoResultFault("No vendor found!");
				}
			} else {
				throw new InvalidInputFault("Invalid requestId!");
			}
		} catch (InvalidInputFault | NoResultFault e) {
			return CommonUtils.getFailedMessage(null, e.getMessage());
		}
		return CommonUtils.getSuccessMessage(list);
	}

}
