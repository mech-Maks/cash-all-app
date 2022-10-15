package com.tinkoff.com.tinkoff.financialtracker.converter;

import com.tinkoff.com.tinkoff.financialtracker.domain.Category;
import com.tinkoff.com.tinkoff.financialtracker.domain.Colour;
import com.tinkoff.com.tinkoff.financialtracker.domain.Icon;
import com.tinkoff.com.tinkoff.financialtracker.model.CategoryDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.CategoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryConverter {
    private final CategoryRepository categoryRepository;

    public CategoryConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Cacheable("icons")
    public List<Icon> getIcons() {
        return categoryRepository.getIcons();
    }

    @Cacheable("colours")
    public List<Colour> getColours() {
        return categoryRepository.getColours();
    }

    private Long getIconIdByName(String iconName) {
        return getIcons().stream()
                .filter(i -> i.getIconName().equals(StringUtils.trimToEmpty(iconName)))
                .map(Icon::getId)
                .findFirst()
                .orElse(null);
    }

    private String getIconNameById(Long iconId) {
        return getIcons().stream()
                .filter(i -> i.getId().equals(iconId))
                .map(Icon::getIconName)
                .findFirst()
                .orElse(null);
    }

    private Long getColourIdByName(String colourName) {
        return getColours().stream()
                .filter(c -> c.getColourName().equals(StringUtils.trimToEmpty(colourName)))
                .map(Colour::getId)
                .findFirst()
                .orElse(null);
    }

    private String getColourNameById(Long colourId) {
        return getColours().stream()
                .filter(c -> c.getId().equals(colourId))
                .map(Colour::getColourName)
                .findFirst()
                .orElse(null);
    }

    public Category convert(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        if (getIconIdByName(categoryDto.getIconName()) == null || getColourIdByName(categoryDto.getColourName()) == null) {
            throw new IllegalArgumentException("No such colour or icon");
        }

        return new Category()
                .setId(categoryDto.getId())
                .setName(categoryDto.getName())
                .setUserId(categoryDto.getUserId())
                .setIsDefault(categoryDto.getIsDefault())
                .setColourId(getColourIdByName(categoryDto.getColourName()))
                .setIconId(getIconIdByName(categoryDto.getIconName()))
                .setOperationType(categoryDto.getOperationType());
    }

    public CategoryDto convert(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryDto()
                .setId(category.getId())
                .setName(category.getName())
                .setUserId(category.getUserId())
                .setIsDefault(category.getIsDefault())
                .setColourName(getColourNameById(category.getColourId()))
                .setIconName(getIconNameById(category.getIconId()))
                .setOperationType(category.getOperationType());
    }
}
