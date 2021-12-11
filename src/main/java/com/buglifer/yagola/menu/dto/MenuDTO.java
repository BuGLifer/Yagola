package com.buglifer.yagola.menu.dto;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class MenuDTO extends CommentDTO {

    private String name;
    private long price;
    private String imgLink;
    private RestaurantDTO restaurant;

    @Builder(
            builderClassName = "menuEntity"
            , builderMethodName = "fromMenuEntity"
    )
    public MenuDTO(MenuEntity menuEntity) {
        setName(menuEntity.getName());
        setPrice(menuEntity.getPrice());
        setImgLink(menuEntity.getImgLink());
        restaurant = RestaurantDTO
                .initRestaurant()
                .seq(menuEntity.getRestaurant().getSeq())
                .build();
    }

    @Builder(
            builderClassName = "dto"
            , builderMethodName = "initMenu"
    )
    public MenuDTO(long seq) { setSeq(seq); }
}
