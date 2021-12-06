package com.mohamadou.springfooddeliveryorderapi.repository;

import com.mohamadou.springfooddeliveryorderapi.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}