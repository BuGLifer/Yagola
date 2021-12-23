package com.buglifer.yagola.order.repository;

import com.buglifer.yagola.common.domain.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {
}
