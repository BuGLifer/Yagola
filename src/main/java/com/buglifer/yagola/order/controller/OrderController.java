package com.buglifer.yagola.order.controller;

import com.buglifer.yagola.order.dto.OrderDTO;
import com.buglifer.yagola.order.dto.UserOrderDTO;
import com.buglifer.yagola.order.service.OrderService;
import com.buglifer.yagola.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("orders")
@RestController
public class OrderController {

    private OrderService orderService;

    // 주문 조회
    @GetMapping("{seq}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable(name = "seq", required = true) long seq) {
        return ResponseEntity.ok().body(orderService.findOrderBySeq(seq));
    }

    // 주문 생성
    @PostMapping()
    public ResponseEntity<OrderDTO> postOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok().body(orderService.saveOrder(orderDTO));
    }

    // 주문 삭제
    @DeleteMapping("{seq}")
    public ResponseEntity deleteOrder(@PathVariable(name = "seq") long seq, @RequestBody UserDTO userDTO) {
        orderService.removeOrder(
                OrderDTO
                        .forDelete()
                        .seq(seq)
                        .userDTO(userDTO)
                        .build()
        );
        return ResponseEntity.noContent().build();
    }

    // 주문 상태 수정
    @PatchMapping("{seq}")
    public ResponseEntity<OrderDTO> patchOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok().body(orderService.modifyOrder(orderDTO));
    }

    // order-seq 주문 참여 생성
    @PostMapping("{seq}")
    public ResponseEntity<UserOrderDTO> postJoinOrder(@PathVariable(name = "seq") long seq, @RequestBody UserOrderDTO userOrderDTO) {
        userOrderDTO.getOrder().setSeq(seq);
        return ResponseEntity.ok().body(orderService.joinOrder(userOrderDTO));
    }
}
