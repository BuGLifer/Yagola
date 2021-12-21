package com.buglifer.yagola.common.batch.service;

import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.menu.repository.MenuRepository;
import com.buglifer.yagola.menu.search.MenuSearch;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BatchService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public List<RestaurantDTO> getRestaurants() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();
        return restaurantEntityList.stream().map(e -> RestaurantDTO
                .fromEntity()
                .entity(e)
                .build()).collect(Collectors.toList());
    }

    
}
