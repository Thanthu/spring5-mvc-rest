package com.thanthu.springmvcrest.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CatorgoryListDTO {
	
	List<CategoryDTO> categories;

}
