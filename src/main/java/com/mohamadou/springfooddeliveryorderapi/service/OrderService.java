package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.*;
import com.mohamadou.springfooddeliveryorderapi.reponse.CustomerOrderResponse;
import com.mohamadou.springfooddeliveryorderapi.repository.CustomerRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.MenuItemRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.PlacedOrderRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.StatusOrderRepository;
import com.mohamadou.springfooddeliveryorderapi.request.CustomerOrderRequest;
import com.mohamadou.springfooddeliveryorderapi.request.OrderDetailsRequest;
import com.mohamadou.springfooddeliveryorderapi.request.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private  OrderRepository orderRepository;
    private  MenuItemRepository menuItemRepository;
    private  PlacedOrderRepository placedOrderRepository;
    private  CustomerRepository customerRepository;
    private  StatusOrderRepository statusOrderRepository;
    private CustomerOrderRequest customerOrderRequest;

    public OrderService() {

    }

    @Autowired
    public OrderService(OrderRepository orderRepository, MenuItemRepository menuItemRepository,
                        PlacedOrderRepository placedOrderRepository, CustomerRepository customerRepository,
                        StatusOrderRepository statusOrderRepository, CustomerOrderRequest customerOrderRequest) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.placedOrderRepository = placedOrderRepository;
        this.customerRepository = customerRepository;
        this.statusOrderRepository = statusOrderRepository;
        this.customerOrderRequest = customerOrderRequest;
    }

    public CustomerOrderResponse createOrder(CustomerOrderRequest request) {
        log.info("CustomerOrderRequest: {}",request);

       /* //Calculate the orderPrice( sum of all items in the order)
        Double orderPrice = toItems(request.getOrderDetails()).stream()
                .mapToDouble(OrderDetails::getTotalItemPrice)
                .sum();

        //Calculate the final orderPrice by reducing the order discount
        Double totalOrderPrice = (request.getDiscount() != null) ? orderPrice - request.getDiscount() : orderPrice;
*/
/*

        Optional<StatusOrder> statusOrder = statusOrderRepository.findById(1L);
        
        Customer customer = Customer.builder()
            .firstName(request.getCustomer().getFirstName())
            .lastName(request.getCustomer().getLastName())
            .email(request.getCustomer().getEmail())
            .phone(request.getCustomer().getPhone())
            .address(request.getCustomer().getAddress())
        .build();

       Customer createdCustomer = customerRepository.save(customer);

       PlacedOrder placedOrder = new PlacedOrder();
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
*/

//        CustomerOrderRequest customerOrderRequest = new CustomerOrderRequest();
        PlacedOrder placedOrder = customerOrderRequest.toOrderRequest(request);

        // Save Order
        PlacedOrder savedOrder = orderRepository.save(placedOrder);

        return CustomerOrderResponse.toOrderResponse(savedOrder);
    }

}
