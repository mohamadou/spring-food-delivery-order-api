package com.mohamadou.springfooddeliveryorderapi.repository;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void isShouldCheckIfCategoryExistsById() {
        // Given
        String categoryName = "MyDrinks";
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setDescription("Lorem ipseum");

        categoryRepository.save(category);

        // When
       Category expected = categoryRepository.findCategoryByCategoryName(categoryName);

        // Then
        assertThat(expected.getCategoryName()).isEqualTo(categoryName);
        assertThat(expected.getId()).isNotNull();
    }
}