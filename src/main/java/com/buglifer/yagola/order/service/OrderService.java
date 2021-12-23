package com.buglifer.yagola.order.service;

import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.common.domain.UserOrderEntity;
import com.buglifer.yagola.order.dto.UserOrderDTO;
import com.buglifer.yagola.common.enums.order.Status;
import com.buglifer.yagola.order.dto.OrderDTO;
import com.buglifer.yagola.order.repository.OrderRepository;
import com.buglifer.yagola.order.repository.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserOrderRepository userOrderRepository;

    public OrderDTO findOrderBySeq(long seq) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(seq);
        if (!optionalOrderEntity.isPresent()) throw new EntityNotFoundException("해당 주문이 존재하지 않습니다.");
        return OrderDTO
                .fromEntity()
                .entity(optionalOrderEntity.get())
                .build();
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        orderDTO.setStatus(Status.ONLINE);
        OrderEntity orderEntity = OrderEntity
                .fromDTO()
                .dto(orderDTO)
                .build();
        return OrderDTO
                .fromEntity()
                .entity(orderRepository.save(orderEntity))
                .build();
    }

    public void removeOrder(OrderDTO orderDTO) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderDTO.getSeq());
        if (!optionalOrderEntity.isPresent()) throw new EntityNotFoundException("해당 주문이 존재하지 않습니다.");
        if (optionalOrderEntity.get().getUser().getSeq() != orderDTO.getUser().getSeq()) throw new SecurityException("주문 생성자만 삭제 가능합니다.");
        orderRepository.delete(optionalOrderEntity.get());
    }

    public OrderDTO modifyOrder(OrderDTO orderDTO) {
        OrderDTO copyOrderDTO = OrderDTO
                                .fromEntity()
                                .entity(orderRepository.findById(orderDTO.getSeq()).get())
                                .build();
        if (copyOrderDTO.getUser().getSeq() != orderDTO.getUser().getSeq()) throw new SecurityException("주문 생성자만 수정가능 합니다.");
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
        OrderEntity orderEntity = OrderEntity
                .fromDTO()
                .dto(copyOrderDTO)
                .build();
        return OrderDTO
                .fromEntity()
                .entity(orderRepository.save(orderEntity))
                .build();
    }

    public UserOrderDTO joinOrder(UserOrderDTO userOrderDTO) {
        if (userOrderDTO.getOrder().getUser().getSeq() == userOrderDTO.getUser().getSeq()) userOrderDTO.setHost(true);
        UserOrderEntity userOrderEntity = UserOrderEntity
                                            .fromDTO()
                                            .dto(userOrderDTO)
                                            .build();
        return UserOrderDTO
                .fromEntity()
                .entity(userOrderRepository.save(userOrderEntity))
                .build();
    }
}
