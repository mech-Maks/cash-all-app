package com.tinkoff.com.tinkoff.financialtracker.converter;

import com.tinkoff.com.tinkoff.financialtracker.domain.Category;
import com.tinkoff.com.tinkoff.financialtracker.domain.Colour;
import com.tinkoff.com.tinkoff.financialtracker.domain.Icon;
import com.tinkoff.com.tinkoff.financialtracker.model.CategoryDto;
import com.tinkoff.com.tinkoff.financialtracker.repo.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    //TODO change maps to Cache
    private final Map<Long, String> icons;
    private final Map<Long, String> colours;

    public CategoryConverter(CategoryRepository categoryRepository) {
        icons = categoryRepository.getIcons().stream().collect(Collectors.toMap(Icon::getId, Icon::getIconName));
        colours = categoryRepository.getColours().stream().collect(Collectors.toMap(Colour::getId, Colour::getColourName));
    }

    public Category convert(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        if (!icons.containsValue(categoryDto.getIconName()) || !colours.containsValue(categoryDto.getColourName())) {
            throw new IllegalArgumentException("No such colour or icon");
        }

        return new Category()
                .setId(categoryDto.getId())
                .setName(categoryDto.getName())
                .setUserId(categoryDto.getUserId())
                .setIsDefault(categoryDto.getIsDefault())
                .setColourId(
                        colours.entrySet().stream().filter(entry -> categoryDto.getColourName().equals(entry.getValue())).findFirst().get().getKey()
                )
                .setIconId(
                        icons.entrySet().stream().filter(entry -> categoryDto.getIconName().equals(entry.getValue())).findFirst().get().getKey()
                )
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
                .setColourName(colours.get(category.getColourId()))
                .setIconName(icons.get(category.getIconId()))
                .setOperationType(category.getOperationType());
    }
}
