package com.thanthu.springmvcrest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.thanthu.springmvcrest.domain.Category;
import com.thanthu.springmvcrest.model.CategoryDTO;

@Mapper(componentModel="spring")
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	CategoryDTO categoryToCategoryDTO(Category category);
	
}
