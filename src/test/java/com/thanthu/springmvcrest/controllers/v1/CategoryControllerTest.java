package com.thanthu.springmvcrest.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thanthu.springmvcrest.api.v1.model.CategoryDTO;
import com.thanthu.springmvcrest.exxceptions.ResourceNotFoundException;
import com.thanthu.springmvcrest.handlers.RestResponseEntityExceptionHandler;
import com.thanthu.springmvcrest.services.CategoryService;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

	static final String CATEGORY_API_URL = CategoryController.BASE_URL + "/";
	public static final String NAME = "Thanthu";
	public static final Long ID = 1L;
	CategoryDTO categoryDto;

	@Mock
	CategoryService categoryService;

	@InjectMocks
	CategoryController categoryController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();

		categoryDto = new CategoryDTO();
		categoryDto.setId(ID);
		categoryDto.setName(NAME);
	}

	@Test
	public void testListCategories() throws Exception {
		List<CategoryDTO> categories = Arrays.asList(categoryDto);

		when(categoryService.getAllCategories()).thenReturn(categories);

		mockMvc.perform(
				get(CATEGORY_API_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.categories", hasSize(1)));
	}

	@Test
	public void testGetByNameCategories() throws Exception {
		when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDto);

		mockMvc.perform(get("/api/v1/categories/Jim").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME)));
	}

	@Test
	public void testGetByNameNotFound() throws Exception {
		when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(CategoryController.BASE_URL + "/Foo").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}
