package com.mohamadou.springfooddeliveryorderapi.request;

import com.mohamadou.springfooddeliveryorderapi.entity.*;
import com.mohamadou.springfooddeliveryorderapi.repository.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Slf4j
@Service
public class CustomerOrderRequest {
    private CustomerRequest customer;
    private List<OrderDetailsRequest> orderDetails;
    private String deliveryAddress;
    private Double discount;
    private String comment;
    private Long restaurantId;

    // Dependency Injection
    private MenuItemRepository menuItemRepository;
    private StatusOrderRepository statusOrderRepository;
    private CustomerRepository customerRepository;
    private CityRepository orderRepository;
    private RestaurantRepository restaurantRepository;

    public CustomerOrderRequest() {

    }

    @Autowired
    public CustomerOrderRequest(
            MenuItemRepository menuItemRepository,
            StatusOrderRepository statusOrderRepository,
            CustomerRepository customerRepository,
            CityRepository orderRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.menuItemRepository = menuItemRepository;
        this.statusOrderRepository = statusOrderRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public PlacedOrder toOrderRequest(CustomerOrderRequest request) {
        //Calculate the orderPrice( sum of all items in the order)
        Double orderPrice = toItems(request.getOrderDetails()).stream()
                .mapToDouble(OrderDetails::getTotalItemPrice)
                .sum();

        //Calculate the final orderPrice by reducing the order discount
        Double totalOrderPrice = (request.getDiscount() != null) ? orderPrice - request.getDiscount() : orderPrice;

        Optional<StatusOrder> statusOrder = statusOrderRepository.findById(1L);

        Customer customer = Customer.builder()
                .firstName(request.getCustomer().getFirstName())
                .lastName(request.getCustomer().getLastName())
                .email(request.getCustomer().getEmail())
                .phone(request.getCustomer().getPhone())
                .address(request.getCustomer().getAddress())
                .build();
        Customer createdCustomer = customerRepository.save(customer);

        // Get Restaurant from the Db
        Optional<Restaurant> restaurant = restaurantRepository.findById(request.getRestaurantId());


        PlacedOrder placedOrder = new PlacedOrder();
        placedOrder.setRestaurant(restaurant.get());
        placedOrder.addOrderDetails(toItems(request.getOrderDetails()));
        placedOrder.setOrderTime(LocalDateTime.now());
        placedOrder.setEstimatedDeliveryTime(LocalDateTime.now());
        placedOrder.setFoodReadyTime(LocalDateTime.now());
        placedOrder.setActualDeliveryTime(LocalDateTime.now());
        placedOrder.setDeliveryAddress(request.getDeliveryAddress());
        placedOrder.setOrderPrice(orderPrice);
        placedOrder.setTotalOrderPrice(totalOrderPrice);
        placedOrder.setDiscount(request.getDiscount());
        placedOrder.setStatusOrders(List.of(statusOrder.get()));
        placedOrder.setCustomer(createdCustomer);
        placedOrder.setComment(request.getComment());


        return placedOrder;
    }

    private List<OrderDetails> toItems(List<OrderDetailsRequest> orderDetailsRequests) {
        log.info("OrderDetailsRequest: {}", orderDetailsRequests);
        log.info("menuItemRepository: {}", menuItemRepository);

        return orderDetailsRequests.stream()
                .map((orderDetail) -> {
                    log.info("orderDetail.getMenuItemId() : {}",orderDetail.getMenuItemId());
                    Optional<MenuItem> menuItem = menuItemRepository.findById(orderDetail.getMenuItemId());
                    if(menuItem.isEmpty()) {
                        throw new IllegalArgumentException("Menu item does not exist with this id: "+orderDetail.getMenuItemId());
                    }

                    /*Optional<PlacedOrder> placedOrder = placedOrderRepository.findById(orderDetail.getPlacedOrderId());
                    if(placedOrder.isEmpty()) {
                        throw new IllegalArgumentException("Placed Order does not exist with this id: "+orderDetail.getMenuItemId());
                    }*/

                    return OrderDetails.builder()
                            .menuItem(orderDetail.getMenuItemId())
                            .quantity(orderDetail.getQuantity())
                            .itemPrice(orderDetail.getItemPrice())
                            .totalItemPrice(orderDetail.getItemPrice()*orderDetail.getQuantity())
                            .comment(orderDetail.getComment())
                            .build();
                })
                .collect(Collectors.toList());
    }
}