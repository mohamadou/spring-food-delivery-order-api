package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.Customer;
import com.mohamadou.springfooddeliveryorderapi.request.CustomerRequest;
import com.mohamadou.springfooddeliveryorderapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(){

    }

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        //TODO implement Pagination and Sorting
        return  customerService.getAllCustomers();
    }

    @GetMapping(path = "/{customerId}")
    public Customer getCustomerBuId(@PathVariable Long customerId) {
        return  customerService.getCustomerById(customerId);
    }

    @DeleteMapping(path = "/delete/{customerId}")
    public int deleteCustomerById(@PathVariable Long customerId) {
        return  customerService.deleteCustomerById(customerId);
    }

    @PostMapping(path = "/create")
    public Customer createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return  customerService.createCustomer(customerRequest);
    }

    @PutMapping(path = "/edit")
    public Customer updateCustomer(@RequestBody CustomerRequest customerRequest) {
        return  customerService.updateCustomer(customerRequest);
    }
}
