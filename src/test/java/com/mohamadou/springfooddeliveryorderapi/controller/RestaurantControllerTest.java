package com.mohamadou.springfooddeliveryorderapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @MockBean
    RestaurantController restaurantController;

    @Autowired
    MockMvc mockMvc;

    private final String requestMapping = "/restaurant";

    Restaurant restaurant;
    Restaurant restaurant2;

    @BeforeEach
    void setUp() {
        City city1 = new City(1L,"Mermoz", "1234");
        City city2 = new City(2L,"Fann", "9832");
        restaurant = new Restaurant(1L,"Caesar Mermoz","Mermoz 3eme porte coté BICIs", city1,null);
        restaurant2 = new Restaurant(2L,"Tacos de Lyon","Fann, place du souvenir", city2, null);
    }

    @Test
    void getAllRestaurants() throws Exception {
        //given
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant);
        restaurantList.add(restaurant2);
        given(restaurantController.getAllRestaurants()).willReturn(restaurantList);

        //when - then
        mockMvc.perform(get(requestMapping)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getRestaurantById() throws Exception {
        //given
        given(restaurantController.getRestaurantById(anyLong())).willReturn(restaurant);

        //when - then
        mockMvc.perform(get(requestMapping+"/{restaurantId}", restaurant.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(restaurant.getId().intValue())));
    }

    @Test
    void createRestaurant() throws Exception {
        //given
        given(restaurantController.createRestaurant(any())).willReturn(restaurant);

        //when - then
        mockMvc.perform(post(requestMapping+"/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(restaurant)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateRestaurant() throws Exception {
        //given
        given(restaurantController.updateRestaurant(any())).willReturn(restaurant);

        //when - then
        mockMvc.perform(put(requestMapping+"/edit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(restaurant)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void deleteRestaurantById() throws Exception {
        //given
        given(restaurantController.getRestaurantById(anyLong())).willReturn(restaurant);

        //when - then
        mockMvc.perform(delete(requestMapping+"/delete/{restaurantId}", restaurant.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(0)));
    }
}