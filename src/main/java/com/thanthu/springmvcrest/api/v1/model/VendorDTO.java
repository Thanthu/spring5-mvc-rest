package com.thanthu.springmvcrest.api.v1.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

	@Schema(description = "Name of Vendor", required = true)
	@NotEmpty
	private String name;

	@Schema(description = "URL to get Vendor details", accessMode = AccessMode.READ_ONLY)
	@JsonProperty("vendor_url")
	private String vendorUrl;

}
