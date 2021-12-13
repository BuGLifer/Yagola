package com.buglifer.yagola.order.dto;

import com.buglifer.yagola.common.domain.OrderEntity;
import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.enums.order.Status;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import com.buglifer.yagola.user.dto.UserDTO;
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
    private UserDTO user;
    private MenuDTO menu;


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
                        .seq(restaurantEntity.getSeq())
                        .apiID(restaurantEntity.getApiID())
                        .name(restaurantEntity.getName())
                        .tel(restaurantEntity.getTel())
                        .imgLink(restaurantEntity.getImgLink())
                        .category(restaurantEntity.getCategory())
                        .build();
    }

    @Builder(
            builderClassName = "initDTO"
            , builderMethodName = "initOrderDTO"
    )
    public OrderDTO(long seq) {
        setSeq(seq);
    }
}
