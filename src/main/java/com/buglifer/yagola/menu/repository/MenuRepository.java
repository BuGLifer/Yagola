package com.buglifer.yagola.menu.repository;

import com.buglifer.yagola.common.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    List<MenuEntity> findAllByRestaurant(Long restSeq);
}
