/**
 * 
 */
package com.ms.project.rest;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.project.dto.MessageDto;
import com.ms.project.dto.VendorDto;
import com.ms.project.service.VendorLocal;

/**
 * @author Syed.Waris
 *
 */

@RestController
@RequestMapping(value = "/vendor", produces = "applicatoion/json")
public class VendorController {
	@Autowired
	private VendorLocal vendorLocal;

	@RequestMapping(value = "createVendor", method = RequestMethod.POST)
	public MessageDto createVendors(@RequestBody VendorDto dto) {
		return vendorLocal.createVendor(dto);
	}

	@RequestMapping(value = "/bestdeals/{requestId}", method = RequestMethod.GET)
	public MessageDto getBestDealsFromVendors(@PathParam("requestId") int requestId) {
		return vendorLocal.getBestDealsByVendors(requestId);
	}

}
