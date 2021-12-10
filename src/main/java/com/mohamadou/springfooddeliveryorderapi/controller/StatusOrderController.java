package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.StatusOrder;
import com.mohamadou.springfooddeliveryorderapi.service.StatusOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/statusOrder")
public class StatusOrderController {
     StatusOrderService statusOrderService;

    public StatusOrderController() {

    }

    @Autowired
    public StatusOrderController(StatusOrderService statusOrderService) {
        this.statusOrderService = statusOrderService;
    }

    @GetMapping
    public List<StatusOrder> getAllStatusOrders() {
        return statusOrderService.getAllStatusOrders();
    }

    @GetMapping(path = "/{statusOrderId}")
    public Optional<StatusOrder> getStatusOrderById(@PathVariable Long statusOrderId) {
        return statusOrderService.getStatusOrderById(statusOrderId);
    }

    @DeleteMapping(path = "/delete/{statusOrderId}")
    public int deleteStatusOrderById(@PathVariable Long statusOrderId) {
        return statusOrderService.deleteStatusOrderById(statusOrderId);
    }

    @PostMapping(path = "/create")
    public StatusOrder createStatusOrder(@RequestBody StatusOrder statusOrder) {
        return statusOrderService.createStatusOrder(statusOrder);
    }

    @PutMapping(path = "/edit")
    public StatusOrder updateStatusOrder(@RequestBody StatusOrder statusOrder) {
        return statusOrderService.updateStatusOrder(statusOrder);
    }

}
