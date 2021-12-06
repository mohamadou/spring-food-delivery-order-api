package com.mohamadou.springfooddeliveryorderapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * All menu items included in offers are stored in the in_offer table.
 * This table contains the UNIQUE pair of offer_id – menu_item_id.
 */

@Entity
@Table(name = "menu_item")
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String itemName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;
    private String ingredients;
    private String recipe;
    private Double price;
    private Boolean active;

    @ManyToMany
    @JoinTable(
            name = "in_offer",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private List<Offer> offer;

}
