package com.mohamadou.springfooddeliveryorderapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
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
@ToString
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

    // TODO add Restaurant to menuItem

    // TODO add menu_item_image (Type: Blob)


    @ManyToMany
    @JoinTable(
            name = "in_offer",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private List<Offer> offers;

    public void addOffer(Offer offer) {
        if(this.offers == null) {
            this.offers = new ArrayList<>();
        }
        this.offers.add(offer);
    }

    /*@JsonIgnore
    @OneToMany(mappedBy = "menuItem",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<OrderDetails> orderDetails;*/

}
