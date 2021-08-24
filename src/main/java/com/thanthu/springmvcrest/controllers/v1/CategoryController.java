package com.thanthu.springmvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thanthu.springmvcrest.api.v1.model.CategoryDTO;
import com.thanthu.springmvcrest.api.v1.model.CatorgoryListDTO;
import com.thanthu.springmvcrest.services.CategoryService;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<CatorgoryListDTO> getallCatetories() {
		return new ResponseEntity<CatorgoryListDTO>(new CatorgoryListDTO(categoryService.getAllCategories()),
				HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(name), HttpStatus.OK);
	}

}