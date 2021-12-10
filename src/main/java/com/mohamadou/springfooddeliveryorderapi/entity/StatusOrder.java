package com.mohamadou.springfooddeliveryorderapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "status_order")
@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String statusName;

    @ManyToMany
    @JoinTable(
            name = "status_placed_order",
            joinColumns = @JoinColumn(name = "status_order_id"),
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private List<PlacedOrder> placedOrders;

    //TODO add added_date column in the status_placed_order table
    
}