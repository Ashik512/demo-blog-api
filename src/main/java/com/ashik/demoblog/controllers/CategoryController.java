package com.ashik.demoblog.controllers;

import com.ashik.demoblog.payloads.ApiResponse;
import com.ashik.demoblog.payloads.CategoryDto;
import com.ashik.demoblog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // create category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto createCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

    }

    // update category
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") Integer catId) {

        CategoryDto updateCategory = categoryService.updateCategory(categoryDto, catId);
        return ResponseEntity.ok(updateCategory);

    }

    // delete category
    @DeleteMapping("/{catId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("catId") Integer catId) {

        categoryService.deleteCategory(catId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
    }

    // getting all category
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {

        List<CategoryDto> allCategory = categoryService.getAllCategory();

        return ResponseEntity.ok(allCategory);

    }

    // getting single category
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("catId") Integer catId) {

        CategoryDto categoryDto = categoryService.getCategoryById(catId);

        return ResponseEntity.ok(categoryDto);
    }

}
