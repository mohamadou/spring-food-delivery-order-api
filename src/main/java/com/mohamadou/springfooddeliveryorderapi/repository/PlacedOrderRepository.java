package com.mohamadou.springfooddeliveryorderapi.repository;

import com.mohamadou.springfooddeliveryorderapi.entity.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacedOrderRepository extends JpaRepository<PlacedOrder, Long> {
}
