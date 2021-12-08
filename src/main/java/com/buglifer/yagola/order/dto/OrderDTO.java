package com.buglifer.yagola.order.dto;

import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.enums.order.Status;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
        if(restaurant != null) {
            restaurant.getOrders().remove(this);
        }
        restaurant = new RestaurantDTO(orderEntity.getRestaurant());
        if(!restaurant.getOrders().contains(this)) {
            restaurant.setOrder(this);
        }
    }
}
