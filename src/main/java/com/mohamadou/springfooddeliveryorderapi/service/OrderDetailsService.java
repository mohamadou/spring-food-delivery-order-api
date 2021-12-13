package com.mohamadou.springfooddeliveryorderapi.service;

import com.mohamadou.springfooddeliveryorderapi.entity.MenuItem;
import com.mohamadou.springfooddeliveryorderapi.entity.OrderDetails;
import com.mohamadou.springfooddeliveryorderapi.entity.PlacedOrder;
import com.mohamadou.springfooddeliveryorderapi.repository.MenuItemRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.OrderDetailsRepository;
import com.mohamadou.springfooddeliveryorderapi.repository.PlacedOrderRepository;
import com.mohamadou.springfooddeliveryorderapi.request.OrderDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {
    OrderDetailsRepository orderDetailsRepository;
    PlacedOrderRepository placedOrderRepository;
    MenuItemRepository menuItemRepository;

    public OrderDetailsService() {

    }

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository,
                               PlacedOrderRepository placedOrderRepository,
                               MenuItemRepository menuItemRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.placedOrderRepository = placedOrderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> getOrderDetailsById(Long orderDetailsId){
        return  orderDetailsRepository.findById(orderDetailsId);
    }

    public int deleteOrderDetailsById(Long orderDetailsId){
        Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findById(orderDetailsId);

        if(optionalOrderDetails.isEmpty()) {
            throw new IllegalArgumentException("Order Details with id: "+ orderDetailsId + " does not exists");
        }
        orderDetailsRepository.deleteById(orderDetailsId);

        return 0;
    }

/*    public OrderDetails createOrderDetails(OrderDetailsRequest orderDetailsRequest){
        orderDetailsRequest.setId(0L);
        OrderDetails orderDetails = new OrderDetails();

        //Check if placed order exist before adding it to orderdetails
        Optional<PlacedOrder> optionalPlacedOrder = placedOrderRepository.findById(orderDetailsRequest.getPlacedOrderId());
        if(optionalPlacedOrder.isEmpty()) {
            orderDetails.setPlacedOrder(null);
        }
        orderDetails.setPlacedOrder(optionalPlacedOrder.get());

        //Check if menu item exist before adding it to orderDetails
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(orderDetailsRequest.getMenuItemId());
        if(optionalMenuItem.isEmpty()) {
            orderDetails.setPlacedOrder(null);
            // If menuItem does not exist the order details creation is canceled
            throw new IllegalArgumentException("The menu item does with "+orderDetailsRequest.getMenuItemId()+" does not exist");
        }
        orderDetails.setMenuItem(optionalMenuItem.get());

        orderDetails.setQuantity(orderDetailsRequest.getQuantity());
        orderDetails.setItemPrice(orderDetailsRequest.getItemPrice());
        orderDetails.setTotalItemPrice(orderDetailsRequest.getItemPrice() * orderDetailsRequest.getQuantity());
        orderDetails.setComment(orderDetailsRequest.getComment());

        return orderDetailsRepository.save(orderDetails);
    }

    public OrderDetails updateOrderDetails(OrderDetailsRequest orderDetailsRequest){
        Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findById(orderDetailsRequest.getId());

        if(optionalOrderDetails.isEmpty()) {
            throw new IllegalArgumentException("Order Details with id: "+ orderDetailsRequest.getId() + " does not exists");
        }

        OrderDetails orderDetails = optionalOrderDetails.get();

        //Check if placed order exist before adding it to orderdetails
        Optional<PlacedOrder> optionalPlacedOrder = placedOrderRepository.findById(orderDetailsRequest.getPlacedOrderId());
        if(optionalPlacedOrder.isEmpty()) {
            orderDetails.setPlacedOrder(null);
        }
        orderDetails.setPlacedOrder(optionalPlacedOrder.get());

        //Check if menu item exist before adding it to orderDetails
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(orderDetailsRequest.getMenuItemId());
        if(optionalMenuItem.isEmpty()) {
            orderDetails.setPlacedOrder(null);
            // If menuItem does not exist the order details creation is canceled
            throw new IllegalArgumentException("The menu item does with "+orderDetailsRequest.getMenuItemId()+" does not exist");
        }
        orderDetails.setMenuItem(optionalMenuItem.get());

        orderDetails.setQuantity(orderDetailsRequest.getQuantity());
        orderDetails.setItemPrice(orderDetailsRequest.getItemPrice());
        orderDetails.setTotalItemPrice(orderDetailsRequest.getItemPrice() * orderDetailsRequest.getQuantity());
        orderDetails.setComment(orderDetailsRequest.getComment());

        return orderDetailsRepository.save(orderDetails);
    }*/
}
