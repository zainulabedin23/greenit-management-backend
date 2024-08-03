package net.javaguides.greenit_management.mapper;


import net.javaguides.greenit_management.dto.CategoryDto;
import net.javaguides.greenit_management.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }

    public Category toEntity(CategoryDto CategoryDto) {
        Category category = new Category();
        category.setCategoryId(CategoryDto.getCategoryId());
        category.setCategoryName(CategoryDto.getCategoryName());
        return category;
    }
}