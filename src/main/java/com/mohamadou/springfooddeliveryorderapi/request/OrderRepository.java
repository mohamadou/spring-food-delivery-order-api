package com.mohamadou.springfooddeliveryorderapi.request;

import com.mohamadou.springfooddeliveryorderapi.entity.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<PlacedOrder, Long> {
}
