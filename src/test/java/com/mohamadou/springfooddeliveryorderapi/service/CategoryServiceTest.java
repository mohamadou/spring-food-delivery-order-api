package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.CategoryRepository;
import com.mohamadou.springfooddeliveryorderapi.request.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
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
        // when
        categoryService.getAllCategories();
        // then
        verify(categoryRepository).findAll();
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
        CategoryRequest categoryRequest = new CategoryRequest(null,"Pizza","Lorem ipseum");
        Category category = new Category(null,"Pizza","Lorem ipseum");
        given(categoryRepository.save(any(Category.class))).willReturn(category);


        //when
        Category expected = categoryService.createCategory(categoryRequest);

        //then
        //ArgumentCaptor<CategoryRequest> categoryArgumentCaptor = ArgumentCaptor.forClass(CategoryRequest.class);

        verify(categoryRepository).save(any(Category.class));
        assertThat(expected).isEqualTo(category);

    }

    @Test
    void shouldUpdateCategory() {
        //given
        Category category = new Category(1L,"Pizza","Lorem ipseum");
        CategoryRequest categoryRequest = new CategoryRequest(1L,"Pizza","Lorem ipseum");

        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(category));


        //when
        Category expected = categoryService.updateCategory(categoryRequest);

        //then
        verify(categoryRepository).save(any(Category.class));
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

}