package com.thanthu.springmvcrest.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thanthu.springmvcrest.api.v1.model.VendorDTO;
import com.thanthu.springmvcrest.domain.Vendor;

class VendorMapperTest {

	static final Long ID = 1L;
	static final String NAME = "Thanthu";

	@Test
	void testVendorToVendorDTO() {
		// given
		Vendor vendor = new Vendor();
		vendor.setId(ID);
		vendor.setName(NAME);

		// when
		VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);

		// then
		assertNotNull(vendorDTO);
		assertEquals(vendor.getName(), vendorDTO.getName());
	}

	@Test
	void testVendorDtoToVendor() {
		// given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(NAME);

		// when
		Vendor vendor = VendorMapper.INSTANCE.vendorDtoToVendor(vendorDTO);

		// then
		assertNotNull(vendor);
		assertEquals(vendorDTO.getName(), vendor.getName());
	}

}
