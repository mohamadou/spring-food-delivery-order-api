package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.entity.Customer;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.CityRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.CustomerRepository;
import com.mohamadou.springfooddeliveryorderapi.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    CityRepository cityRepository;

    public CustomerService() {

    }

    @Autowired
    public CustomerService(CustomerRepository customerService, CityRepository cityRepository) {
        this.customerRepository = customerService;
        this.cityRepository = cityRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer id not found :" + customerId);
        }
        return customer.get();
    }

    public Customer createCustomer(CustomerRequest customerRequest) {
        customerRequest.setId(0L);

        // Check if the city exist before creating the customer
        Optional<City> optionalCity = cityRepository.findById(customerRequest.getCityId());
        Customer customer = new Customer();

       if (optionalCity.isEmpty()) {
           throw new ResourceNotFoundException("City id not found :" + customerRequest.getCityId());
       } else {
            customer.setCity(optionalCity.get());
       }

        if(customerRequest.getEmail() != null) {
            customer.setEmail(customerRequest.getEmail());
        }

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhone(customerRequest.getPhone());
        customer.setPassword(customerRequest.getPassword());
        customer.setTimeJoined(LocalDate.now());

        return  customerRepository.save(customer);
    }

    public Customer updateCustomer(@Valid CustomerRequest customerRequest) {
        // Check if the customer exists before updating it
        Optional<Customer> customerOptional = customerRepository.findById(customerRequest.getId());
        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer id not found :" + customerRequest.getId());
        }

        // TODO validate phone before saving in DB

        // Check if the city exist before updating the customer
        Optional<City> optionalCity = cityRepository.findById(customerRequest.getCityId());
        Customer customer = customerOptional.get();

        if (optionalCity.isEmpty()) {
            throw new ResourceNotFoundException("City id not found :" + customerRequest.getCityId());
        } else {
            customer.setCity(optionalCity.get());
        }

        if(customerRequest.getEmail() != null) {
            customer.setEmail(customerRequest.getEmail());
        }

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhone(customerRequest.getPhone());
        customer.setPassword(customerRequest.getPassword());

        return  customerRepository.save(customer);
    }

    public int deleteCustomerById(Long customerId) {
        // Check if the customer exists before deleting it
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("City id not found :" + customerId);
        }

         customerRepository.deleteById(customerId);
         return 0;
    }

}
