package com.thanthu.springmvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thanthu.springmvcrest.api.v1.model.CategoryDTO;
import com.thanthu.springmvcrest.api.v1.model.CatorgoryListDTO;
import com.thanthu.springmvcrest.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "APIs to interact with CAtegory", name = "Category APIs")
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

	public static final String BASE_URL = "/api/v1/categories";

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Operation(summary = "Get list of Categories")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CatorgoryListDTO getallCatetories() {
		return new CatorgoryListDTO(categoryService.getAllCategories());
	}

	@Operation(summary = "Get Category by name")
	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable String name) {
		return categoryService.getCategoryByName(name);
	}

}
