package com.icc.orders_svc.services;

import com.icc.orders_svc.domain.OrderRequestDTO;
import com.icc.orders_svc.models.Order;
import com.icc.orders_svc.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.icc.orders_svc.models.OrderItem;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrderFromDTO(OrderRequestDTO orderRequest) {
        Order order = new Order();
        order.setAccountId(orderRequest.getAccountId());
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = orderRequest.getProducts().stream()
                .map(product -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(product.getProductId());
                    item.setQuantity(product.getQuantity());
                    item.setPrice(product.getPrice());
                    item.setOrder(order);
                    return item;
                }).toList();

        order.setItems(items);

        return orderRepository.save(order);
    }

    public List<Order> getOrderHistory(Long accountId) {
        return orderRepository.findByAccountId(accountId);
    }
}
