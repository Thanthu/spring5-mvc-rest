package com.thanthu.springmvcrest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.thanthu.springmvcrest.domain.Category;
import com.thanthu.springmvcrest.model.CategoryDTO;

class CategoryMapperTest {

	private static final String NAME = "Thanthu";
	private static final long ID = 1L;
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	@Test
	void testCategoryToCategoryDTO() {

		// given
		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);
		
		// when
		CategoryDTO categoryDTO = categoryMapper.INSTANCE.categoryToCategoryDTO(category);
		
		// then
		assertEquals(category.getId(), categoryDTO.getId());
		assertEquals(category.getName(), categoryDTO.getName());
	}

}
