package com.ashik.demoblog.services;

import com.ashik.demoblog.payloads.CategoryDto;
import com.ashik.demoblog.payloads.UserDto;

import java.util.List;

public interface CategoryService {

    // create
    CategoryDto createCategory(CategoryDto categoryDto);

    // update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer userId);

    // get
    CategoryDto getCategoryById(Integer userId);

    // get all
    List<CategoryDto> getAllCategory();

    // delete
    void deleteCategory(Integer catId);

}
