package com.tutorial.Tutorial.controller;

import com.tutorial.Tutorial.dto.CategoryDTO;
import com.tutorial.Tutorial.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @Operation(summary = "Create category")
    @PostMapping
    @PreAuthorize("hasRole('Author')")
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryDTO request) {
        categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Category id Not found")
    })
    @Operation(summary = "Update category by id", description = "Update category")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Author')")
    public ResponseEntity<String> updateCategory(@Valid @PathVariable Long id, @RequestBody CategoryDTO request) {
        categoryService.updateCategory(id, request);
        return ResponseEntity.ok("Successfully updated");
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Founded all categories"),
                    @ApiResponse(responseCode = "404", description = "didn't found any category")
            }
    )
    @GetMapping("/all")
    @PreAuthorize("hasRole('Author')")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @Operation(summary = "Get category by id", description = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category founded successfully"),
            @ApiResponse(responseCode = "404", description = "Category id not found"),

    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Author')")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @Operation(summary = "Delete category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category id not found"),
            @ApiResponse(responseCode = "409", description = "Could not execute statement")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Author')")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category with ID " + id + " has been successfully deleted.");
    }
}
