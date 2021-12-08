package com.buglifer.yagola.order.dto;

import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.enums.order.Status;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends CommonDTO {

    private Status status;
    private Date offlineTime;
    private Date orderTime;
    private Date arrivalTime;
    private RestaurantDTO restaurant;

    public OrderDTO(OrderEntity orderEntity) {
        if(orderEntity == null) {
            return;
        }
        setSeq(orderEntity.getSeq());
        setCreatedTime(orderEntity.getCreatedTime());
        status = orderEntity.getStatus();
        offlineTime = orderEntity.getOfflineTime();
        orderTime = orderEntity.getOrderTime();
        arrivalTime = orderEntity.getArrivalTime();
        RestaurantEntity restaurantEntity = orderEntity.getRestaurant();
        restaurant =
                new RestaurantDTO()
                        .builder()
                        .apiID(restaurantEntity.getApiID())
                        .name(restaurantEntity.getName())
                        .tel(restaurantEntity.getTel())
                        .imgLink(restaurantEntity.getImgLink())
                        .category(restaurantEntity.getCategory())
                        .build();
    }
}
