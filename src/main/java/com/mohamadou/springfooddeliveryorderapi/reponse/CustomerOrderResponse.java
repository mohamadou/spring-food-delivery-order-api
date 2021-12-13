package com.mohamadou.springfooddeliveryorderapi.reponse;

import com.mohamadou.springfooddeliveryorderapi.entity.Customer;
import com.mohamadou.springfooddeliveryorderapi.entity.OrderDetails;
import com.mohamadou.springfooddeliveryorderapi.entity.PlacedOrder;
import com.mohamadou.springfooddeliveryorderapi.entity.StatusOrder;
import com.mohamadou.springfooddeliveryorderapi.request.CustomerRequest;
import com.mohamadou.springfooddeliveryorderapi.request.OrderDetailsRequest;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerOrderResponse {
    private Long id;
    private List<OrderDetails> orderDetails;
    private Customer customer;
    private String deliveryAddress;
    private Double discount;
    private String comment;
    private LocalDateTime orderTime;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
    private LocalDateTime foodReadyTime;
    private Double orderPrice;
    private Double totalOrderPrice;
    private List<StatusOrder> statusOrders;

    public static CustomerOrderResponse toOrderResponse(PlacedOrder placedOrder) {
        // Order Response mapping
        CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
        customerOrderResponse.setId(placedOrder.getId());
        customerOrderResponse.setOrderDetails(placedOrder.getOrderDetails());
        customerOrderResponse.setOrderTime(placedOrder.getOrderTime());
        customerOrderResponse.setEstimatedDeliveryTime(placedOrder.getEstimatedDeliveryTime());
        customerOrderResponse.setFoodReadyTime(placedOrder.getFoodReadyTime());
        customerOrderResponse.setActualDeliveryTime(placedOrder.getActualDeliveryTime());
        customerOrderResponse.setDeliveryAddress(placedOrder.getDeliveryAddress());
        customerOrderResponse.setOrderPrice(placedOrder.getOrderPrice());
        customerOrderResponse.setTotalOrderPrice(placedOrder.getTotalOrderPrice());
        customerOrderResponse.setDiscount(placedOrder.getDiscount());
        customerOrderResponse.setStatusOrders(placedOrder.getStatusOrders());
        customerOrderResponse.setCustomer(placedOrder.getCustomer());
        customerOrderResponse.setComment(placedOrder.getComment());

        return customerOrderResponse;
    }
}
