package com.mohamadou.springfooddeliveryorderapi.repository;

import com.mohamadou.springfooddeliveryorderapi.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}