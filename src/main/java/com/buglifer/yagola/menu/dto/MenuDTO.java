package com.buglifer.yagola.menu.dto;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuDTO extends CommonDTO {

    private String name;
    private long price;
    private String imgLink;
    private RestaurantDTO restaurant;

    @Builder(
            builderClassName = "entity"
            , builderMethodName = "fromEntity"
    )
    private MenuDTO(MenuEntity entity) {
        setName(entity.getName());
        setPrice(entity.getPrice());
        setImgLink(entity.getImgLink());
        if (entity.getRestaurant() != null) {
            restaurant = RestaurantDTO
                    .initRestaurant()
                    .seq(entity.getRestaurant().getSeq())
                    .build();
        }
    }

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initMenu"
    )
    private MenuDTO(long seq) { setSeq(seq); }
}
