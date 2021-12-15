package com.mohamadou.springfooddeliveryorderapi.request;

import com.mohamadou.springfooddeliveryorderapi.entity.MenuItem;
import com.mohamadou.springfooddeliveryorderapi.entity.PlacedOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder @ToString
public class OrderDetailsRequest {

    private Long id;

    @NotNull(message = "PlacedOrderId is required")
    private Long placedOrderId;

    /**
     * References the menu_item table,
     * but only if this record is related to a menu item and not an offer.
     */
    @NotEmpty(message = "MenuItemId is required")
    private Long menuItemId;

    /**
     * The number of offers or menu items included in this order.
     */
    private Long quantity;

    /**
     * The price of a single offer or menu item.
     */
    private Double itemPrice;

    /**
     *  Any comments inserted by the customer that relate specifically to that order item,
     *  e.g. “Please cut pizza into 8 slices”
     */
    private String comment;

}
