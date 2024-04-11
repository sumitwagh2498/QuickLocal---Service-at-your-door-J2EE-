package com.quicklocal.controller;

import com.quicklocal.dto.OrderDto;
import com.quicklocal.exception.ServiceServiceException;
import com.quicklocal.model.Order;
import com.quicklocal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin // Update with your frontend server's URL
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/{serviceProviderId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Integer serviceProviderId, @RequestBody OrderDto orderDto) throws ServiceServiceException {
        Order orderEntity = orderService.placeOrder(orderDto, serviceProviderId);
        if (orderEntity != null) {
            return new ResponseEntity<>(orderEntity, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
