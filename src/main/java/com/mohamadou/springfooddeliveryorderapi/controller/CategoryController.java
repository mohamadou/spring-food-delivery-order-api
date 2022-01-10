package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.request.CategoryRequest;
import com.mohamadou.springfooddeliveryorderapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController() {

    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService  = categoryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping(path = "/delete/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int deleteCategoryById(@PathVariable Long categoryId) {
        return categoryService.deleteCategoryById(categoryId);
    }

    @PostMapping(path = "/create", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping(path = "/edit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category updateCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryRequest);
    }

}
