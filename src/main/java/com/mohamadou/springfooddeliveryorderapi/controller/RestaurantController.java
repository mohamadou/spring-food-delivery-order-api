package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.Restaurant;
import com.mohamadou.springfooddeliveryorderapi.request.RestaurantRequest;
import com.mohamadou.springfooddeliveryorderapi.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/restaurant")
@Slf4j
public class RestaurantController {

    RestaurantService restaurantService;

    public RestaurantController() {
    }

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllCities();
    }

    @GetMapping(path = "/{restaurantId}")
    public Optional<Restaurant> getRestaurantById(@PathVariable  Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @PostMapping(path = "/create")
    public Restaurant createRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return  restaurantService.createRestaurant(restaurantRequest);
    }

    @PutMapping(path = "/edit")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        return  restaurantService.updateRestaurant(restaurant);
    }

    @DeleteMapping(path = "/delete/{restaurantId}")
    public int deleteRestaurantById(@PathVariable Long restaurantId) {
        return restaurantService.deleteRestaurant(restaurantId);
    }

}
