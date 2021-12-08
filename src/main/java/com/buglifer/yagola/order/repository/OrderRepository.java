package com.buglifer.yagola.order.repository;

import com.buglifer.yagola.common.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
