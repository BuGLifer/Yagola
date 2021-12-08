package com.buglifer.yagola.order.controller;

import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.order.dto.OrderDTO;
import com.buglifer.yagola.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RequestMapping("orders")
@RestController
public class OrderController {

    private OrderRepository orderRepository;

    @GetMapping("{seq}")
    public OrderDTO getOrder(@PathVariable(name = "seq", required = true) long seq) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(seq);
        if(optionalOrderEntity.isEmpty()) {
            return null;
        }
        return new OrderDTO(optionalOrderEntity.get());
    }
}
