package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
import com.mohamadou.springfooddeliveryorderapi.exception.ResourceNotFoundException;
import com.mohamadou.springfooddeliveryorderapi.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService() {

    }

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        //TODO : implement Pagination and sSorting

        return cityRepository.findAll();
    }

    public City getCityById(Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        if(city.isEmpty()) {
            throw new ResourceNotFoundException("City id not found :" + cityId);
        }

        return city.get();
    }

    public City createCity(City city) {
        city.setId(0L);
        return cityRepository.save(city);
    }

    public City updateCity(City city) {
        Optional<City> optionalCity = cityRepository.findById(city.getId());

        if(optionalCity.isEmpty()) {
            throw new ResourceNotFoundException("City id not found :" + city.getId());
        }

        return cityRepository.save(city);
    }

    public int deleteCityById(Long cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);

        if(optionalCity.isEmpty()) {
            throw new ResourceNotFoundException("City id not found :" + cityId);
        }

        // TODO: detach restaurant when deleting city

        cityRepository.deleteById(cityId);
        return 0;
    }
}
