package com.buglifer.yagola.order.service;

import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.order.dto.OrderDTO;
import com.buglifer.yagola.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDTO findOrder(long seq) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(seq);
        if (optionalOrderEntity.isPresent()) {
            return new OrderDTO(optionalOrderEntity.get());
        }
        return new OrderDTO();
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = OrderEntity
                .saveOrder()
                .dto(orderDTO)
                .build();
        return new OrderDTO(orderRepository.save(orderEntity));
    }
}
