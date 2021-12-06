package com.mohamadou.springfooddeliveryorderapi.repository;

import com.mohamadou.springfooddeliveryorderapi.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}