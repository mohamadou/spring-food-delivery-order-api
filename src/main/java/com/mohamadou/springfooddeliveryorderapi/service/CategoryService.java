package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.repository.CategoryRepository;
import com.mohamadou.springfooddeliveryorderapi.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    private CategoryService() {
    }

    @Autowired
    private CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public int deleteCategoryById(Long categoryId) {
        // Check if category exists before deleting the category
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isEmpty()) {
            throw new RuntimeException("Customer with id: "+ categoryId+" does not exist");
        }
        categoryRepository.deleteById(categoryId);

        return 0;
    }

    public Category createCategory(@Valid CategoryRequest categoryRequest) {
        Category category = new Category();
        categoryRequest.setId(0L);
        category.setCategoryName(categoryRequest.getCategoryName());

        return categoryRepository.save(category);
    }

    public Category updateCategory(@Valid CategoryRequest categoryRequest) {
        // Check if category exists before updating the category
        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequest.getId());
        if(optionalCategory.isEmpty()) {
            throw new RuntimeException("Customer with id: "+ categoryRequest.getId()+" does not exist");
        }
        Category category = optionalCategory.get();
        category.setCategoryName(categoryRequest.getCategoryName());

        return categoryRepository.save(category);
    }
}
