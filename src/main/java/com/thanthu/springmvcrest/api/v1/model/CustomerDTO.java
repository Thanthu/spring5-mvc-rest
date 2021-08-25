package com.thanthu.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private String firstname;
	private String lastname;
	
	@JsonProperty("customer_url")
	private String customerUrl;

}
