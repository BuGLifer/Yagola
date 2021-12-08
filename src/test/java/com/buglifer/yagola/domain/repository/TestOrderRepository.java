package com.buglifer.yagola.domain.repository;

import com.buglifer.yagola.common.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestOrderRepository extends JpaRepository<OrderEntity, Long> {
}
