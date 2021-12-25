package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.entity.Customer;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.CityRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.CustomerRepository;
import com.mohamadou.springfooddeliveryorderapi.request.CustomerRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    void shouldGetAllCustomers() {
        //given
        Customer customer = new Customer();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        given(customerRepository.findAll()).willReturn(customerList);

        //when
        List<Customer> expected = customerService.getAllCustomers();

        //then
        then(customerRepository).should().findAll();
        assertThat(expected).hasSize(1);
    }

    @Test
    void shouldGetCustomerById() {
        //given
        Long customerId = 1L;
        Customer customer = new Customer();
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));

        //when
        Customer expected = customerService.getCustomerById(customerId);

        //then
        then(customerRepository).should().findById(anyLong());
        assertThat(expected).isNotNull().isInstanceOf(Customer.class);
    }

    @Test
    void shouldThrowExceptionWhenGetCustomerById() {
        //given
        Long customerId = 1L;
        Customer customer = new Customer();
        given(customerRepository.findById(anyLong())).willThrow(ResourceNotFoundException.class);

        //when
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(anyLong()));

        //then
        then(customerRepository).should().findById(anyLong());

    }

    @Test
    void shoulCreateCustomer() {
        //given
        Customer customer = new Customer();
        City city = new City(1L, null, null);
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCityId(city.getId());

        given(cityRepository.findById(city.getId())).willReturn(Optional.of(city));
        given(customerRepository.save(any(Customer.class))).willReturn(customer);

        //when
        Customer expected = customerService.createCustomer(customerRequest);

        //then
        then(cityRepository).should().findById(city.getId());
        then((customerRepository)).should().save(any(Customer.class));
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldUpdateCustomer() {
        //given
        Customer customer = new Customer();
        City city = new City(1L, null, null);
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId(1L);
        customerRequest.setCityId(city.getId());

        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));
        given(cityRepository.findById(city.getId())).willReturn(Optional.of(city));
        given(customerRepository.save(any(Customer.class))).willReturn(customer);

        //when
        Customer expected = customerService.updateCustomer(customerRequest);

        //then
        then(customerRepository).should().findById(anyLong());
        then(cityRepository).should().findById(city.getId());
        then((customerRepository)).should().save(any(Customer.class));
        assertThat(expected).isNotNull();
    }

    @Test
    void deleteCustomerById() {
        //given
        Long customerId = 1L;
        Customer customer = new Customer();
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));

        //when
        int expected = customerService.deleteCustomerById(customerId);

        //then
        then(customerRepository).should().findById(anyLong());
        then(customerRepository).should().deleteById(anyLong());
        assertThat(expected).isEqualTo(0);
    }
}