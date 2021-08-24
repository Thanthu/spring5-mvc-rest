package com.thanthu.springmvcrest.services;

import java.util.List;

import com.thanthu.springmvcrest.api.v1.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryByName(String name);

}
