package com.mohamadou.springfooddeliveryorderapi.bootstrap;

import com.mohamadou.springfooddeliveryorderapi.entity.*;
import com.mohamadou.springfooddeliveryorderapi.repository.*;
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

    @Autowired
    StatusOrderRepository statusOrderRepository;

    @Autowired MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) throws Exception {
        //Remove all data from the DB
        restaurantRepository.deleteAll();
        cityRepository.deleteAll();
        customerRepository.deleteAll();
        categoryRepository.deleteAll();
        statusOrderRepository.deleteAll();
        menuItemRepository.deleteAll();

        // City
        City city1 = new City(1L,"Mermoz", "1234");
        City city2 = new City(2L,"Fann", "9832");
        City city3 = new City(3L,"Sacre Coeur 3", "4555");
        City city4 = new City(4L,"Liberté 6", "9993");
        cityRepository.saveAllAndFlush(List.of(city1, city2, city3, city4));


        // Restaurant
       Restaurant restaurant1 = new Restaurant(1L,"Caesar Mermoz","Mermoz 3eme porte coté BICIs", city1,null);
       Restaurant restaurant2 = new Restaurant(2L,"Tacos de Lyon","Fann, place du souvenir", city2, null);
       Restaurant restaurant3 = new Restaurant(3L,"Chez joe","Coté Boulangerie jaune", city3, null);
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
        Category pizza = new Category(1L, "Pizza", null);
        Category salads = new Category(2L, "Salads", null);
        Category sandwiches = new Category(3L, "Sandwiches", null);
        Category drinks = new Category(4L, "Drinks", null);
        categoryRepository.saveAll(List.of(pizza, salads, sandwiches, drinks));

        // Menu Items
        MenuItem menuItem1 = new MenuItem(0L, "DILLON JAMES", pizza, null, "Mozzarella, asiago, fresh chopped basil, garlic, sliced tomatoes, red sauce", null, 10.0, true,null);
        MenuItem menuItem2 = new MenuItem(0L, "CALEXICO", pizza, null, "Mozzarella, gorgonzola, chicken, jalapenos, hot buffalo sauce, red sauce", null, 10.0, true,null);
        MenuItem menuItem3 = new MenuItem(0L, "CASPIAN", pizza, null, "Mozzarella, gorgonzola, bbq chicken, barbecue sauce, sliced red onions", null, 10.0, true,null);
        MenuItem menuItem4 = new MenuItem(0L, "PIZZA SALAD", pizza, null, "Create your own salad on a warm asiago pizza crust", null, 10.0, true,null);
        MenuItem menuItem5 = new MenuItem(0L, "JASPER", pizza, null, "Mozzarella, mushrooms, spicy chicken sausage, red sauce", null, 10.0, true,null);
        menuItemRepository.saveAll(List.of(
                menuItem1, menuItem2, menuItem3,
                menuItem4, menuItem5
        ));

        // Status Order
        StatusOrder statusOrder1 = new StatusOrder(0L, "Order confirmed", null);
        StatusOrder statusOrder2 = new StatusOrder(0L, "Processing in progress", null);
        StatusOrder statusOrder3 = new StatusOrder(0L, "Canceled", null);
        StatusOrder statusOrder4 = new StatusOrder(0L, "Shipped", null);
        StatusOrder statusOrder5 = new StatusOrder(0L, "Delivered", null);
        StatusOrder statusOrder6 = new StatusOrder(0L, "Payment accepted", null);
        StatusOrder statusOrder7 = new StatusOrder(0L, "Awaiting cash on delivery", null);
        StatusOrder statusOrder8 = new StatusOrder(0L, "Refunded", null);
        statusOrderRepository.saveAll(List.of(
                statusOrder1, statusOrder2, statusOrder3,
                statusOrder4, statusOrder5, statusOrder6,
                statusOrder7, statusOrder8
        ));
    }
}
