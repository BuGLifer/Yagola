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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDTO extends CommonDTO {

    private Status status;
    private Date offlineTime;
    private Date orderTime;
    private Date arrivalTime;
    private RestaurantDTO restaurant;
    private UserDTO user;
    private MenuDTO menu;

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
        if (entity.getRestaurant() != null) {
            restaurant = RestaurantDTO
                          .initRestaurant()
                          .seq(entity.getRestaurant().getSeq())
                          .build();
        }
        if (entity.getUser() != null) {
            user = UserDTO
                    .initUser()
                    .seq(entity.getUser().getSeq())
                    .build();
        }
    }

    @Builder(
            builderClassName = "delete"
            , builderMethodName = "forDelete"
    )
    private OrderDTO(long seq, UserDTO userDTO) {
        setSeq(seq);
        setUser(
                UserDTO
                .initUser()
                .seq(userDTO.getSeq())
                .build()
        );
    }
}
