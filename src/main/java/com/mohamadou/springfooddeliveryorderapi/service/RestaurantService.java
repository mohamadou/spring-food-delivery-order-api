package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.entity.Restaurant;
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

    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        return  restaurantRepository.findById(restaurantId);
    }

    public Restaurant createRestaurant(RestaurantRequest restaurantRequest) {
        Optional<City> optionalCity = cityRepository.findById(restaurantRequest.getCityId());
        if (optionalCity.isEmpty()) {
            throw new RuntimeException("City with this id: "+ restaurantRequest.getCityId() +" does not exist");
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
            throw new RuntimeException("Restaurant with this id: "+ restaurant.getId() +" does not exist");
        }

        return restaurantRepository.save(restaurant);
    }

    public int deleteRestaurant(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalRestaurant.isEmpty()) {
            throw new RuntimeException("Restaurant with this id: "+ restaurantId +" does not exist");
        }

         restaurantRepository.deleteById(restaurantId);

        return 0;
    }
}
