package com.thanthu.springmvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanthu.springmvcrest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

}
