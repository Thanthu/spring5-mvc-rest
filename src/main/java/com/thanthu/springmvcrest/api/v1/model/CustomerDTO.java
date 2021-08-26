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
public class CustomerDTO {

	@Schema(description = "Firstname of Customer", required = true)
	@NotEmpty
	private String firstname;
	
	@Schema(description = "Lastname of Customer", required = true)
	@NotEmpty
	private String lastname;
	
	@Schema(description = "URL to get Customer details", accessMode = AccessMode.READ_ONLY)
	@JsonProperty("customer_url")
	private String customerUrl;

}
