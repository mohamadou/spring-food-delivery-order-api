package com.mohamadou.springfooddeliveryorderapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "status_type")
@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class StatusType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String statusName;

    @ManyToMany
    @JoinTable(
            name = "order_status",
            joinColumns = @JoinColumn(name = "status_type_id"),
            inverseJoinColumns = {@JoinColumn(name = "order_id"), @JoinColumn(name = "order_id")})
    private List<PlacedOrder> placedOrders;

    //TODO add added_date column in the order_status table
    
}