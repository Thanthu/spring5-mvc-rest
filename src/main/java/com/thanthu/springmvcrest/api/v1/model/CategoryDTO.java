package com.thanthu.springmvcrest.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

	@Schema(description = "ID of Category")
	private Long id;

	@Schema(description = "Name of Category")
	private String name;

}
