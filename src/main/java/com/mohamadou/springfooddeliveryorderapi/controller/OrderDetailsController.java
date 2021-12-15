package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.OrderDetails;
import com.mohamadou.springfooddeliveryorderapi.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orderDetails")
public class OrderDetailsController {

    OrderDetailsService orderDetailsService;

    public OrderDetailsController() {

    }

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }

    @GetMapping(path = "{orderDetailsId}")
    public OrderDetails getOrderDetailsById(@PathVariable  Long orderDetailsId){
        return  orderDetailsService.getOrderDetailsById(orderDetailsId);
    }

    @DeleteMapping(path = "/delete/{orderDetailsId}")
    public int deleteOrderDetailsById(@PathVariable Long orderDetailsId){
        return orderDetailsService.deleteOrderDetailsById(orderDetailsId);
    }

  /*  @PostMapping(path = "/create")
    public OrderDetails createOrderDetails(@RequestBody OrderDetailsRequest orderDetailsRequest){
        return orderDetailsService.createOrderDetails(orderDetailsRequest);
    }

    @PutMapping(path = "/edit")
    public OrderDetails updateOrderDetails(@RequestBody OrderDetailsRequest orderDetailsRequest){
        return orderDetailsService.updateOrderDetails(orderDetailsRequest);
    }*/

}
