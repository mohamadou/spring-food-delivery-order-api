package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.entity.Restaurant;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.CityRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.RestaurantRepository;
import com.mohamadou.springfooddeliveryorderapi.request.RestaurantRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RestaurantService {
    RestaurantRepository restaurantRepository;
    CityRepository cityRepository;

    public RestaurantService() {
    }

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, CityRepository cityRepository) {
        this.restaurantRepository = restaurantRepository;
        this.cityRepository = cityRepository;
    }

    public List<Restaurant> getAllCities() {
        return  restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalRestaurant.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant id not found :" + restaurantId);
        }

        return optionalRestaurant.get();
    }

    public Restaurant createRestaurant(RestaurantRequest restaurantRequest) {
        Optional<City> optionalCity = cityRepository.findById(restaurantRequest.getCityId());
        if (optionalCity.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant id not found :" + restaurantRequest.getCityId());
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setCity(optionalCity.get());
        restaurant.setName(restaurantRequest.getName());
        restaurant.setAddress(restaurantRequest.getAddress());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurant.getId());

        if (optionalRestaurant.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant id not found :" + restaurant.getId());
        }

        return restaurantRepository.save(restaurant);
    }

    public int deleteRestaurant(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalRestaurant.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant id not found :" + restaurantId);
        }

         restaurantRepository.deleteById(restaurantId);

        return 0;
    }
}
