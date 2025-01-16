package com.tutorial.Tutorial.service.impl;

import com.tutorial.Tutorial.mapper.CategoryMapper;
import com.tutorial.Tutorial.dto.CategoryDTO;
import com.tutorial.Tutorial.entity.Category;
import com.tutorial.Tutorial.repository.CategoryRepository;
import com.tutorial.Tutorial.service.CategoryService;
import com.tutorial.Tutorial.exception.ResourceAlreadyExistException;
import com.tutorial.Tutorial.exception.ResourceNotFoundByGivenID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryDTO request) {

        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            log.error("Category {} already exist!", request.getCategoryName());
            throw new ResourceAlreadyExistException("Category name-" + request.getCategoryName() + " already exist!!");
        }
        Category category = categoryRepository.save(categoryMapper.toEntity(request));

    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        log.info("searching for given {}", id);
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundByGivenID("Category not found by given " + id));
        log.info("Getting category by id" + category.toString());
        return categoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("Searching for all categories");
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateCategory(Long id, CategoryDTO categoryDTO) {
        log.info("try to update category by given {}", id);
        categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setId(categoryDTO.getId());
                    existingCategory.setCategoryName(categoryDTO.getCategoryName());
                    existingCategory.setCreatedAt(categoryDTO.getCreatedAt());
                    existingCategory.setUpdatedAt(LocalDateTime.now());
                    Category updatedCategory = categoryRepository.save(existingCategory);
                    return categoryMapper.toDTO(updatedCategory);
                })
                .orElseThrow(() -> new ResourceNotFoundByGivenID("Category with ID " + id + " not found"));

    }

    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category {} id", id);
        categoryRepository.deleteById(id);
    }
}