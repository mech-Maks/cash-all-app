package com.tinkoff.com.tinkoff.financialtracker.controllers;

import com.tinkoff.com.tinkoff.financialtracker.model.CategoryDto;
import com.tinkoff.com.tinkoff.financialtracker.service.CategoryService;
import com.tinkoff.com.tinkoff.financialtracker.utils.OperationType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user/category")
@Tag(description = "Api to manage category",
        name = "Category Resource")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get categories for user",
            description = "Provides all categories for user")
    @GetMapping(value = "/all/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getCategories(@PathVariable Long userId){
        return categoryService.findAllCategories(userId);
    }

    @Operation(summary = "Get filter categories for user",
            description = "Provides CONSUMPTION/INCOME categories for user. Query param: type = CONSUMPTION/INCOME")
    @GetMapping(value = "/filter/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getFilterCategories(@PathVariable Long userId, @RequestParam("type") OperationType operationType){
        return categoryService.findFilterCategories(userId, operationType);
    }

    @Operation(summary = "Get category for user",
            description = "Provides category by id")
    @GetMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto getCategory(@PathVariable Long categoryId){
        return categoryService.findCategory(categoryId);
    }

    @Operation(summary = "Create category",
            description = "Creates new category")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @Operation(summary = "Delete category for user",
            description = "Delete the desired category for user.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(name = "id") Long id){
        return categoryService.deleteCategory(id);
    }

    @Operation(summary = "Update category",
            description = "Provides new updated category")
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }
}