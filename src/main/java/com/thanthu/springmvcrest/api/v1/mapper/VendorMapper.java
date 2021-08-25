package com.thanthu.springmvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.thanthu.springmvcrest.api.v1.model.VendorDTO;
import com.thanthu.springmvcrest.domain.Vendor;

@Mapper(componentModel = "spring")
public interface VendorMapper {

	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

	VendorDTO vendorToVendorDTO(Vendor vendor);

	Vendor vendorDtoToVendor(VendorDTO vendorDTO);

}
