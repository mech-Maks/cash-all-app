package com.tinkoff.com.tinkoff.financialtracker.service;

import com.tinkoff.com.tinkoff.financialtracker.converter.CategoryConverter;
import com.tinkoff.com.tinkoff.financialtracker.domain.Category;
import com.tinkoff.com.tinkoff.financialtracker.model.CategoryDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.CategoryRepository;
import com.tinkoff.com.tinkoff.financialtracker.utils.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public List<CategoryDto> findAllCategories(Long userId) {
        return categoryRepository.findAllByUserId(userId).orElseThrow(()-> new IllegalArgumentException("No such userId")).stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        categoryRepository.findById(categoryDto.getId())
                .map(category -> updateCategory(category, categoryConverter.convert(categoryDto)))
                .map(categoryRepository::save);
        return categoryConverter.convert(categoryRepository.findById(categoryDto.getId()).orElse(null));
    }

    public ResponseEntity<Object> deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.ifPresent(entity -> {
            entity.setIsDeleted(true);
            categoryRepository.deleteCategory(entity.getId());
        });
        category.orElseThrow(() -> new IllegalArgumentException("no such CategoryId"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("success", true, "message", "The operation was successfull!"));
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryConverter.convert(categoryRepository.save(
                categoryConverter.convert(categoryDto)
                        .setIsDefault(false)
                        .setIsDeleted(false)
                        .setCreatedAt(LocalDate.now())
        ));

    }

    private Category updateCategory(Category oldCategory, Category newCategory) {
        return oldCategory.setName(newCategory.getName())
                .setColourId(newCategory.getColourId())
                .setIconId(newCategory.getIconId())
                .setOperationType(newCategory.getOperationType());
    }

    public CategoryDto findCategory(Long categoryId) {
        return categoryConverter.convert(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("no such categoryId")));
    }

    public List<CategoryDto> findFilterCategories(Long userId, OperationType operationType) {
        return categoryRepository.findAllByUserIdAndOperationType(userId, operationType).orElseThrow(()-> new IllegalArgumentException("No such userId")).stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }
}