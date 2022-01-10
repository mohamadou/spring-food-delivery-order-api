package com.mohamadou.springfooddeliveryorderapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.request.CategoryRequest;
import com.mohamadou.springfooddeliveryorderapi.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @MockBean
    CategoryService categoryService;

    @Autowired
    MockMvc mockMvc;

    private String requestMapping;
    private Category category;
    private Category category2;


    @BeforeEach
    void setUp() {
        requestMapping = "/category";

        category = new Category();
        category.setId(1L);
        category.setCategoryName("Pizza");
        category.setDescription("Very good pizza");

        category2 =  new Category();
        category2.setId(2L);
        category2.setCategoryName("Hamburger");
        category2.setDescription("Double piece hamburger");
    }

    @Test
    void shouldGetAllCategories() throws Exception{
        // given
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        categoryList.add(category2);
        given(categoryService.getAllCategories()).willReturn(categoryList);

        // when - then
         mockMvc.perform(get(requestMapping)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$", hasSize(2)));

        then(categoryService).should().getAllCategories();

    }

    @Test
    void shouldGetCategoryById() throws Exception {
        //given
        given(categoryService.getCategoryById(anyLong())).willReturn(category);

        //when - then
        mockMvc.perform(get(requestMapping+"/"+category.getId())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.categoryName", is("Pizza")));

        then(categoryService).should().getCategoryById(anyLong());
    }

    @Test
    void shouldDeleteCategoryById() throws Exception {
        //given
        given(categoryService.deleteCategoryById(anyLong())).willReturn(0);

        //when - then
        mockMvc.perform(delete(requestMapping+"/delete/"+category.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", is(0)));

        then(categoryService).should().deleteCategoryById(anyLong());
    }

    @Test
    void createCategory() throws Exception{
        //given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("Pizza mini");

        given(categoryService.createCategory(any())).willReturn(category);

        //when - then
        mockMvc.perform(post(requestMapping+"/create")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").exists());
    }

    @Test
    void updateCategory() throws Exception {
        //given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(1L);
        categoryRequest.setCategoryName("Pizza mini");

        given(categoryService.updateCategory(any())).willReturn(category);

        //when - then
        mockMvc.perform(put(requestMapping+"/edit")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").exists());
    }

    //Convert Object to JsonString
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}