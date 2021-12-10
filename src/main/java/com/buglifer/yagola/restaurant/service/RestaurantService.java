package com.buglifer.yagola.restaurant.service;

import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDTO getRestaurant(long seq) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(seq);
        if (!optionalRestaurantEntity.isPresent()) {
            return restaurantDTO;
        }
        return RestaurantDTO
                .fromEntity()
                .entity(optionalRestaurantEntity.get())
                .build();
    }

}
