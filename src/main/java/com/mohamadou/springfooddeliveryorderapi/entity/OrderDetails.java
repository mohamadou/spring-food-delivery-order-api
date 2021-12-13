package com.mohamadou.springfooddeliveryorderapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder @ToString
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "placed_order_id")
    private PlacedOrder placedOrder;

    /**
     * References the menu_item table,
     * but only if this record is related to a menu item and not an offer.
     */
    /*@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "menu_item_id")*/
    private Long menuItem;

    /**
     * The number of offers or menu items included in this order.
     */
    private Long quantity;

    /**
     * The price of a single offer or menu item.
     */
    private Double itemPrice;

    /**
     * The total price for this line, expressed as item_price * quantity.
     */
    private Double totalItemPrice;

    /**
     *  Any comments inserted by the customer that relate specifically to that order item,
     *  e.g. “Please cut pizza into 8 slices”
     */
    private String comment;

}
