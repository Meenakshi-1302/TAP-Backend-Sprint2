package com.rts.tap.service;

import org.springframework.web.multipart.MultipartFile;

public interface VendorOrganizationProfileService {
	String updateOrganizationLogo(Long vendorId,MultipartFile vendorOrganizationLogo);
	

}
