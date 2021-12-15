package com.mohamadou.springfooddeliveryorderapi.controller;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/city")
public class CityController {

    private CityService cityService;

    public CityController(){}

    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping(path = "/{cityId}")
    public City getCityById(@PathVariable Long cityId) {
        return cityService.getCityById(cityId);
    }

    @PostMapping(path = "/create")
    public City createCity(@RequestBody City city) {
        return  cityService.createCity(city);
    }

    @PutMapping(path = "/edit")
    public City updateCity(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @DeleteMapping(path = "/delete/{cityId}")
    public int deleteCityById(@PathVariable  Long cityId) {
        return cityService.deleteCityById(cityId);
    }
}
