package com.buglifer.yagola.restaurant.controller;

import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.restaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("restaurants")
@RestController
public class RestaurantController {
    private RestaurantRepository restaurantRepository;

    @GetMapping("{seq}")
    public RestaurantDTO getRestaurant(@PathVariable(name = "seq") long seq) {
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(seq);
        if(optionalRestaurantEntity.isEmpty()) {
            return null;
        }
        return new RestaurantDTO(optionalRestaurantEntity.get());
    }
}
