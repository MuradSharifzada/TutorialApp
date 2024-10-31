package com.tutorial.Tutorial.service;

import com.tutorial.Tutorial.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();

    void updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);

}
