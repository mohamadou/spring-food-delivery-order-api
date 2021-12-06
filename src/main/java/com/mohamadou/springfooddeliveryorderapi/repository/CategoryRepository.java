package com.mohamadou.springfooddeliveryorderapi.repository;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    interface OfferRepository extends JpaRepository<Offer, Long> {
    }
}
