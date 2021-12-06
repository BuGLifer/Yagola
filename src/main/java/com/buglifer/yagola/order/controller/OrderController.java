package com.buglifer.yagola.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController("order")
public class OrderController {

    @GetMapping("/")
    public void getOrder() {

    }

    @PostMapping("/")
    public void saveOrder() {

    }

    @DeleteMapping("/")
    public void deleteOrder() {

    }

    @PostMapping("/{orderID}")
    public void joinOrder(@PathVariable(value = "orderID") int orderID) {

    }
}
