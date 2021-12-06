package com.mohamadou.springfooddeliveryorderapi.bootstrap;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.entity.Customer;
import com.mohamadou.springfooddeliveryorderapi.entity.Restaurant;
import com.mohamadou.springfooddeliveryorderapi.repository.CategoryRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.CityRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.CustomerRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        //Remove all data from the DB
        cityRepository.deleteAll();
        restaurantRepository.deleteAll();
        customerRepository.deleteAll();
        categoryRepository.deleteAll();

        // City
        City city1 = new City(1L,"Mermoz", "1234");
        City city2 = new City(2L,"Fann", "9832");
        City city3 = new City(3L,"Sacre Coeur 3", "4555");
        City city4 = new City(4L,"Liberté 6", "9993");

        cityRepository.saveAllAndFlush(List.of(city1, city2, city3, city4));


        // Restaurant
       Restaurant restaurant1 = new Restaurant(1L,"Caesar Mermoz","Mermoz 3eme porte coté BICIs", city1);
       Restaurant restaurant2 = new Restaurant(2L,"Tacos de Lyon","Fann, place du souvenir", city2);
       Restaurant restaurant3 = new Restaurant(3L,"Chez joe","Coté Boulangerie jaune", city3);

       restaurantRepository.saveAll(List.of(restaurant1, restaurant2, restaurant3));

       // Customer
      /*  "firstName": "John",
                "lastName": "Doe",
                "email": "johndoe@gmail.com",
                "phone": "78933838838",
                "address": "Cité Keur gorgui",
                "cityId": 3,
                "password": "12345*/
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setPhone("389499449");

        customerRepository.save(customer1);

        // Category
        Category pizza = new Category(0L, "Pizza");
        Category salads = new Category(0L, "Salads");
        Category sandwiches = new Category(0L, "Sandwiches");
        Category drinks = new Category(0L, "Drinks");
        categoryRepository.saveAll(List.of(pizza, salads, sandwiches, drinks));

    }
}
