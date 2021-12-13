package com.buglifer.yagola.order.dto;

import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.enums.order.Status;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import lombok.*;

import java.util.Date;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends CommonDTO {

    private Status status;
    private Date offlineTime;
    private Date orderTime;
    private Date arrivalTime;
    private RestaurantDTO restaurant;

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initOrder"
    )
    private OrderDTO(long seq) {
        setSeq(seq);
    }

    @Builder(
            builderClassName = "entity"
            , builderMethodName = "fromEntity"
    )
    private OrderDTO(OrderEntity entity) {
        if(entity == null) {
            return;
        }
        setSeq(entity.getSeq());
        setCreatedTime(entity.getCreatedTime());
        status = entity.getStatus();
        offlineTime = entity.getOfflineTime();
        orderTime = entity.getOrderTime();
        arrivalTime = entity.getArrivalTime();
        RestaurantEntity restaurantEntity = entity.getRestaurant();
        if(entity.getRestaurant() != null) {
            restaurant = RestaurantDTO
                    .initRestaurant()
                    .seq(entity.getRestaurant().getSeq())
                    .build();
        }
    }
}
