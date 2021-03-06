package com.buglifer.yagola.restaurant.controller;

import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.restaurant.repository.RestaurantRepository;
import com.buglifer.yagola.restaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("restaurants")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("{seq}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable(name = "seq") long seq) {
        return ResponseEntity.ok().body(restaurantService.findRestaurantBySeq(seq));
    }

    @PostMapping("")
    public ResponseEntity<RestaurantDTO> postRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return ResponseEntity.ok().body(restaurantService.saveRestaurant(restaurantDTO));
    }
}
