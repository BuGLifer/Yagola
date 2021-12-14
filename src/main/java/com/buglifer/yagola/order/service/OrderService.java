package com.buglifer.yagola.order.service;

import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.common.enums.order.Status;
import com.buglifer.yagola.order.dto.OrderDTO;
import com.buglifer.yagola.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
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
        orderDTO.setStatus(Status.ONLINE);
        OrderEntity orderEntity = OrderEntity
                .initOrder()
                .dto(orderDTO)
                .build();
        return new OrderDTO(orderRepository.save(orderEntity));
    }

    public long removeOrder(long seq) {
        Optional<OrderEntity> menuEntity = orderRepository.findById(seq);
        if (!menuEntity.isPresent()) {
            log.error("존재 하지 않는 주문 입니다.");
            return -1;
        }
        orderRepository.delete(menuEntity.get());
        return seq;
    }

    public OrderDTO modifyOrder(OrderDTO orderDTO) {
        // 1안 (seq로 한번 find한 후 변경해서 다시 저장)
        OrderDTO copyOrderDTO = new OrderDTO(orderRepository.findById(orderDTO.getSeq()).get());
        switch (orderDTO.getStatus().name()) {
            case "OFFLINE":
                copyOrderDTO.setOfflineTime(new Date());
                break;
            case "ORDER":
                copyOrderDTO.setOrderTime(new Date());
                break;
            case "ARRIVAL":
                copyOrderDTO.setArrivalTime(new Date());
                break;
        }
        copyOrderDTO.setStatus(orderDTO.getStatus());
        // 2안 (OrderEntity에 @DynamicUpdate 적용) - 오버헤드 발생 할 수 있음
        OrderEntity orderEntity = OrderEntity
                .initOrder()
                .dto(copyOrderDTO)
                .build();
        return new OrderDTO(orderRepository.save(orderEntity));
    }
}
