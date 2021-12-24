package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.CategoryRepository;
import com.mohamadou.springfooddeliveryorderapi.request.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldGetAllCategories() {
        // given
        Category category = new Category();
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        given(categoryRepository.findAll()).willReturn(categoryList);

        // when
        List<Category> foundCategory =categoryService.getAllCategories();

        // then
        then(categoryRepository).should().findAll();
        assertThat(foundCategory).hasSize(1);
    }

    @Test
    void shouldGetCategoryById() {
        //given
        Long id = 1L;
        Category category = new Category(1L,"Pizza","Lorem ipseum");
        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));
        //when
        Category expected = categoryService.getCategoryById(id);
        //then
        verify(categoryRepository).findById(anyLong());
        assertThat(expected).isEqualTo(category);
    }

    @Test
    void shouldThrowExceptionWhenCategoryIdNotExist() {
        //given
        given(categoryRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> categoryService.getCategoryById(anyLong()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageStartingWith("Category id not found :");
    }

    @Test
    void shouldDeleteCategoryById() {
        // given
        Category category = new Category(1L,"Pizza","Lorem ipseum");
        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));

        // when
        int expected = categoryService.deleteCategoryById(anyLong());
        // then
        verify(categoryRepository).deleteById(anyLong());
        assertThat(expected).isEqualTo(0);
    }


    @Test
    void shouldThrowExceptionWhenDeletingCategory() {
        //given
        given(categoryRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> categoryService.deleteCategoryById(anyLong()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageStartingWith("Category id not found :");
    }

    @Test
    void shouldCreateCategory() {
        //given
        CategoryRequest categoryRequest = new CategoryRequest();
        Category category = new Category();
        given(categoryRepository.save(any(Category.class))).willReturn(category);

        //when
        Category expected = categoryService.createCategory(categoryRequest);

        //then
        then(categoryRepository).should().save(any(Category.class));
        assertThat(expected).isNotNull();

    }

    @Test
    void shouldUpdateCategory() {
        //given
        Category category = new Category();
        CategoryRequest categoryRequest = new CategoryRequest(1L,null,null);
        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));

        //when
        Category expected = categoryService.updateCategory(categoryRequest);

        //then
        then(categoryRepository).should().findById(1L);
        then(categoryRepository).should(times(1)).save(any(Category.class));

    }

    @Test
    void shouldThrowExceptionWhenCategoryRequestIsNull() {
        //given
       // given(categoryRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> categoryService.updateCategory(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldThrowExceptionWhenCategoryIdIdNotExist() {
        //given
        CategoryRequest categoryRequest = new CategoryRequest(1L,"Pizza","Lorem ipseum");
        given(categoryRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> categoryService.updateCategory(categoryRequest))
                .isInstanceOf(ResourceNotFoundException.class);
    }


    @Test
    void shouldFindCategoryById() {
        //given
        Long id = 1L;
        Category category = new Category();
        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));

        //when
        Category expected = categoryService.getCategoryById(id);

        //then
        assertThat(expected).isNotNull();
        then(categoryRepository).should().findById(anyLong());
    }

}