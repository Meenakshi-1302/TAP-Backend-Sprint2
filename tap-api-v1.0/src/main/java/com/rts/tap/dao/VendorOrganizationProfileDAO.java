package com.rts.tap.dao;



import com.rts.tap.model.Vendor;

public interface VendorOrganizationProfileDAO {
	Vendor findVendorOrganizationByVendorId(Long vendorId);
	Vendor updateVendorOrganizationLogo(Vendor vendor);

}
