package net.javaguides.greenit_management.service;

import net.javaguides.greenit_management.dto.CategoryDto;
import net.javaguides.greenit_management.entity.Category;
import net.javaguides.greenit_management.mapper.CategoryMapper;
import net.javaguides.greenit_management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDto createCategory(CategoryDto CategoryDto) {
        Category category = categoryMapper.toEntity(CategoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }
    public CategoryDto updateCategory(int categoryId, CategoryDto CategoryDto) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setCategoryName(CategoryDto.getCategoryName());
            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.toDto(updatedCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}