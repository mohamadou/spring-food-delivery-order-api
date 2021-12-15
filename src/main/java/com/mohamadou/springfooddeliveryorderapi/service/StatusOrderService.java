package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.StatusOrder;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusOrderService {
    StatusOrderRepository statusOrderRepository;

    public StatusOrderService() {

    }

    @Autowired
    public StatusOrderService(StatusOrderRepository statusOrderRepository) {
        this.statusOrderRepository = statusOrderRepository;
    }

    public List<StatusOrder> getAllStatusOrders() {
        return statusOrderRepository.findAll();
    }

    public StatusOrder getStatusOrderById(Long statusOrderId){
        Optional<StatusOrder> optionalStatusOrder = statusOrderRepository.findById(statusOrderId);

        if(optionalStatusOrder.isEmpty()) {
            throw new ResourceNotFoundException("Status order id not found :" + statusOrderId);
        }

        return optionalStatusOrder.get();
    }

    public int deleteStatusOrderById(Long statusOrderId){
        Optional<StatusOrder> optionalStatusOrder = statusOrderRepository.findById(statusOrderId);

        if(optionalStatusOrder.isEmpty()) {
            throw new ResourceNotFoundException("Status order id not found :" + statusOrderId);
        }

        statusOrderRepository.deleteById(statusOrderId);

        return 0;
    }

    public StatusOrder createStatusOrder(StatusOrder statusOrder){
        statusOrder.setId(0L);
        return statusOrderRepository.save(statusOrder);
    }

    public StatusOrder updateStatusOrder(StatusOrder statusOrder){
        Optional<StatusOrder> optionalStatusOrder = statusOrderRepository.findById(statusOrder.getId());

        if(optionalStatusOrder.isEmpty()) {
            throw new ResourceNotFoundException("Status order id not found :" + statusOrder.getId());
        }

        return statusOrderRepository.save(statusOrder);
    }
}
