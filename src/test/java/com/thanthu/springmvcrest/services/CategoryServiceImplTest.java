package com.thanthu.springmvcrest.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thanthu.springmvcrest.domain.Category;
import com.thanthu.springmvcrest.mapper.CategoryMapper;
import com.thanthu.springmvcrest.model.CategoryDTO;
import com.thanthu.springmvcrest.repositories.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	CategoryMapper categoryMapper;

	@InjectMocks
	CategoryServiceImpl categoryService;
	
	Category category;
	List<Category> categories;
	final Long CATEGORY_ID = 1L;
	final String CATEGORY_NAME = "Test";
	
	@BeforeEach
	void setUp() throws Exception {
		category = new Category();
		category.setId(CATEGORY_ID);
		category.setName(CATEGORY_NAME);
		
		categories = new ArrayList<Category>();
		categories.add(category);
	}

	@Test
	void testGetAllCategories() {
		when(categoryRepository.findAll()).thenReturn(categories);
		
		List<CategoryDTO> returnedCategories = categoryService.getAllCategories();
		assertEquals(1, returnedCategories.size());
		verify(categoryRepository, times(1)).findAll();
	}

	@Test
	void testGetCategoryByName() {
		when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(category);
		
		CategoryDTO categoryDto = categoryService.getCategoryByName(CATEGORY_NAME);
		assertNotNull(categoryDto);
		assertEquals(CATEGORY_NAME, categoryDto.getName());
		verify(categoryRepository, times(1)).findByName(anyString());
	}

}
