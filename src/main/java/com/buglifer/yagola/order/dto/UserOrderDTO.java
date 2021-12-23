package com.buglifer.yagola.order.dto;

import com.buglifer.yagola.common.domain.UserOrderEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.user.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserOrderDTO  extends CommonDTO {

    private Boolean host = false;
    private UserDTO user;
    private MenuDTO menu;
    private OrderDTO order;
//    private ReviewDTO review;

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initUserOrder"
    )
    public UserOrderDTO(long seq) {
        setSeq(seq);
    }

    @Builder(
            builderClassName = "entity"
            , builderMethodName = "fromEntity"
    )
    public UserOrderDTO(UserOrderEntity entity) {
        setSeq(entity.getSeq());
        setCreatedTime(entity.getCreatedTime());
        if (entity.getUser() != null) {
            user = UserDTO
                    .initUser()
                    .seq(entity.getUser().getSeq())
                    .build();
        }
        if (entity.getMenu() != null) {
            menu = MenuDTO
                    .fromEntity()
                    .entity(entity.getMenu())
                    .build();
        }
        if (entity.getOrder() != null) {
            order = OrderDTO
                     .initOrder()
                     .seq(entity.getOrder().getSeq())
                     .build();
        }
    }
}
