package com.buglifer.yagola.menu.dto;

import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class MenuDTO {

    private String name;
    private long price;
    private String imgLink;

    public MenuDTO(MenuEntity menuEntity) {
        setName(menuEntity.getName());
        setPrice(menuEntity.getPrice());
        setImgLink(menuEntity.getImgLink());
    }
}
