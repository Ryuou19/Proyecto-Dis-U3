package com.icc.orders_svc.controllers;

import com.icc.orders_svc.domain.OrderRequestDTO;
import com.icc.orders_svc.models.Order;
import com.icc.orders_svc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        System.out.println("Received order: " + orderRequest);
        try {
            Order createdOrder = orderService.createOrderFromDTO(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Exception e) {
            e.printStackTrace(); // Mostrar la traza del error para depuración
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping
    public List<Order> getOrdersByAccountId(@RequestParam("accountId") Long accountId) {
        return orderService.getOrderHistory(accountId);
    }
}