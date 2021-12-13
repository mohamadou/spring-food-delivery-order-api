package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.PlacedOrder;
import com.mohamadou.springfooddeliveryorderapi.reponse.CustomerOrderResponse;
import com.mohamadou.springfooddeliveryorderapi.request.CustomerOrderRequest;
import com.mohamadou.springfooddeliveryorderapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController() {
    }

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/create")
    public CustomerOrderResponse createOrder(@RequestBody CustomerOrderRequest customerOrderRequest) {
        return orderService.createOrder(customerOrderRequest);
    }

}
