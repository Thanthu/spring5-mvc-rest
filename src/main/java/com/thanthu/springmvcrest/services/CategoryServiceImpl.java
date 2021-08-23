package com.thanthu.springmvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanthu.springmvcrest.domain.Category;
import com.thanthu.springmvcrest.mapper.CategoryMapper;
import com.thanthu.springmvcrest.model.CategoryDTO;
import com.thanthu.springmvcrest.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	private final CategoryMapper categoryMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll().stream()
				.map(categoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		Category category = categoryRepository.findByName(name);
		return categoryMapper.INSTANCE.categoryToCategoryDTO(category);
	}

}
