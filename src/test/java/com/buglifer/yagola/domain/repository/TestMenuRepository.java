package com.buglifer.yagola.domain.repository;

import com.buglifer.yagola.common.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMenuRepository extends JpaRepository<MenuEntity, Long> {
}
