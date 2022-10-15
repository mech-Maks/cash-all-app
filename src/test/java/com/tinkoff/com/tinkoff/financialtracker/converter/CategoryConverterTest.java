package com.tinkoff.com.tinkoff.financialtracker.converter;

import com.tinkoff.com.tinkoff.financialtracker.domain.Category;
import com.tinkoff.com.tinkoff.financialtracker.domain.Colour;
import com.tinkoff.com.tinkoff.financialtracker.domain.Icon;
import com.tinkoff.com.tinkoff.financialtracker.model.CategoryDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static com.tinkoff.com.tinkoff.financialtracker.utils.OperationType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryConverterTest {
    private final CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);;
    private final CategoryConverter categoryConverter = new CategoryConverter(categoryRepository);

    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    public void init() {
        category = new Category(1L, "category-name", 1L, 2L, 3L, INCOME, false, true, LocalDate.now());
        categoryDto = new CategoryDto(1L, "category-name", 1L, "UIColor.pumpkin", "diamond.circle.fill", INCOME, true);

        when(categoryRepository.getIcons()).thenReturn(List.of(new Icon(3L, "diamond.circle.fill")));
        when(categoryRepository.getColours()).thenReturn(List.of(new Colour(2L, "UIColor.pumpkin")));
    }

    @Test
    public void convertCategoryDto() {
        Category convertedCategory = categoryConverter.convert(categoryDto);

        assertEquals(category.getName(), convertedCategory.getName());
        assertEquals(category.getUserId(), convertedCategory.getUserId());
        assertEquals(category.getIsDefault(), convertedCategory.getIsDefault());
        assertEquals(category.getColourId(), convertedCategory.getColourId());
        assertEquals(category.getIconId(), convertedCategory.getIconId());
        assertEquals(category.getOperationType(), convertedCategory.getOperationType());
    }

    @Test
    public void converCategory() {
        CategoryDto convertedCategoryDto = categoryConverter.convert(category);

        assertEquals(categoryDto.getName(), convertedCategoryDto.getName());
        assertEquals(categoryDto.getUserId(), convertedCategoryDto.getUserId());
        assertEquals(categoryDto.getIsDefault(), convertedCategoryDto.getIsDefault());
        assertEquals(categoryDto.getColourName(), convertedCategoryDto.getColourName());
        assertEquals(categoryDto.getIconName(), convertedCategoryDto.getIconName());
        assertEquals(categoryDto.getOperationType(), convertedCategoryDto.getOperationType());
    }
}
