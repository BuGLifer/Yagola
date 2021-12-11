package com.buglifer.yagola.restaurant.repository;

import com.buglifer.yagola.common.domain.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
