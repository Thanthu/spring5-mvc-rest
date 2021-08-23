package com.thanthu.springmvcrest.services;

import java.util.List;

import com.thanthu.springmvcrest.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryByName(String name);

}
