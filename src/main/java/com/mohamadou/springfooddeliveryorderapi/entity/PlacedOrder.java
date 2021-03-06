package com.mohamadou.springfooddeliveryorderapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class PlacedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The ID of the customer who placed that order.
     * This attribute could contain a NULL value if the order was placed by someone who is not a registered app user.
     */
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "status_placed_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "status_order_id"))
    private List<StatusOrder> statusOrders;

    // TODO: create User entity and join it with the order
//    private Long processedBy;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "placedOrder",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},  fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;

    /**
     * A timestamp of when the order was placed.
     */
    private LocalDateTime orderTime;

    /**
     *  A timestamp of the planned delivery of this order.
     */
    private LocalDateTime estimatedDeliveryTime;

    /**
     * A timestamp denoting when the order items were prepared.
     * This will contain a NULL value until the food is prepared
     */
    private LocalDateTime foodReadyTime;

    /**
     * A timestamp of when this order was actually delivered.
     * It will be NULL until the food is delivered to the customer.
     */
    private LocalDateTime actualDeliveryTime;

    /**
     *  The address where the order should be delivered.
     */
    private String deliveryAddress;

    /**
     * The price for all items included in that order.
     */
    private Double orderPrice;

    /**
     * The amount of discount (e.g. coupon or loyalty discount) applied to the orderPrice, if any.
     */
    private Double discount;

    /**
     * The order price minus the discount.
     */
    private Double totalOrderPrice;

    /**
     * Additional comments inserted by the customer when the order was placed.
     */
    private String comment;


    public void addOrderDetails(List<OrderDetails> orderDetailsList) {
        log.info("PlacedOrder -> addOrderDetails: {}", orderDetails);
        for(OrderDetails orderDetail : orderDetailsList) {
            if (orderDetail != null) {
                if (orderDetails == null) {
                    this.orderDetails = new ArrayList<OrderDetails>();
                }
                this.orderDetails.add(orderDetail);
                orderDetail.setPlacedOrder(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlacedOrder placedOrder = (PlacedOrder) o;
        return id != null && Objects.equals(id, placedOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
