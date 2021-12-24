package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.CityRepository;
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
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CityService cityService;

    @Test
    void shouldGetAllCities() {
        //given
        City city = new City();
        List<City> cityList = new ArrayList<>();
        cityList.add(city);
        given(cityRepository.findAll()).willReturn(cityList);

        //when
        List<City> foundCities = cityService.getAllCities();

        //then
        then(cityRepository).should().findAll();
        assertThat(foundCities).hasSize(1);
    }

    @Test
    void shouldGetCityById() {
        //given
        Long cityId = 1L;
        City city = new City();
        given(cityRepository.findById(anyLong())).willReturn(Optional.of(city));

        //when
        City expected = cityService.getCityById(cityId);

        //then
        then(cityRepository).should().findById(cityId);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowWhenIdOfCityNotExist() {
        //given
        Long cityId = 1L;
        City city = new City();
        given(cityRepository.findById(anyLong())).willThrow(ResourceNotFoundException.class);

        //when
        assertThrows(ResourceNotFoundException.class, () -> cityService.getCityById(cityId));

        //then
        then(cityRepository).should().findById(cityId);
    }

    @Test
    void shouldSaveCity() {
        //given
         City city = new City();
         given(cityRepository.save(any(City.class))).willReturn(city);

        //when
        City expected = cityService.createCity(city);

        //then
        then(cityRepository).should().save(city);
        assertThat(expected).isNotNull().isInstanceOf(City.class);
    }

    @Test
    void shouldUpdateCity() {
        //given
        City city = new City(1L, null, null);
        given(cityRepository.findById(anyLong())).willReturn(Optional.of(city));

        //when
        City expected = cityService.updateCity(city);

        //then
        then(cityRepository).should().save(city);
    }

    @Test
    void shouldThrowWhenUpdating() {
        //given
        Long cityId = 1L;
        City city = new City(1L, null, null);
        given(cityRepository.findById(anyLong())).willThrow(ResourceNotFoundException.class);

        //when
        assertThrows(ResourceNotFoundException.class, () -> cityService.updateCity(city));

        //then
        then(cityRepository).should().findById(cityId);
    }

    @Test
    void deleteCityById() {
        //given
        Long cityId = 1L;
        City city = new City();
        given(cityRepository.findById(anyLong())).willReturn(Optional.of(city));

        //when
        int expected = cityService.deleteCityById(cityId);

        //then
        then(cityRepository).should().deleteById(cityId);
        assertThat(expected).isEqualTo(0);
    }
}