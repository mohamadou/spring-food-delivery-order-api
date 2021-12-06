package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.City;
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

    public Optional<City> getCityById(Long cityId) {
        return cityRepository.findById(cityId);
    }

    public City createCity(City city) {
        city.setId(0L);
        return cityRepository.save(city);
    }

    public City updateCity(City city) {
        Optional<City> optionalCity = cityRepository.findById(city.getId());

        if(optionalCity.isEmpty()) {
            throw new RuntimeException("City with this id: "+ city.getId() +" does not exist");
        }

        return cityRepository.save(city);
    }

    public void deleteCityById(Long cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);

        if(optionalCity.isEmpty()) {
            throw new RuntimeException("City with this id: "+ cityId +" does not exist");
        }

        // TODO: detach restaurant when deleting city

        cityRepository.deleteById(cityId);
        System.out.println("City has been deleted with success");
    }
}
