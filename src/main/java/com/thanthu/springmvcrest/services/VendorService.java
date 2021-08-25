package com.thanthu.springmvcrest.services;

import com.thanthu.springmvcrest.api.v1.model.VendorDTO;
import com.thanthu.springmvcrest.api.v1.model.VendorListDTO;

public interface VendorService {
	
	VendorListDTO getAllVendors();

	VendorDTO getVendorById(Long id);
	
	VendorDTO createNewVendor(VendorDTO VendorDTO);
	
	VendorDTO saveVendorByDTO(Long id, VendorDTO VendorDTO);
	
	VendorDTO patchVendor(Long id, VendorDTO VendorDTO);
	
	void deleteVendorById(Long id);

}
