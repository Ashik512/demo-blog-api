package com.ashik.demoblog.services.impl;

import com.ashik.demoblog.entities.Category;
import com.ashik.demoblog.exceptions.ResourceNotFoundException;
import com.ashik.demoblog.payloads.CategoryDto;
import com.ashik.demoblog.repositories.CategoryRepo;
import com.ashik.demoblog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = convertToEntity(categoryDto);
        Category savedCategory = categoryRepo.save(category);

        return convertToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {

        Category category = categoryRepo.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepo.save(category);

        return convertToDto(updatedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        Category category = categoryRepo.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));

        return convertToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categoryList = categoryRepo.findAll();

        List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> convertToDto(category)).collect(Collectors.toList());

        return categoryDtoList;
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category category = categoryRepo.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));

      categoryRepo.delete(category);
    }

    private Category convertToEntity(CategoryDto categoryDto) {

        Category category = new Category();

        //category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return category;
    }

    private CategoryDto convertToDto(Category category) {

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());

        return categoryDto;
    }

}
