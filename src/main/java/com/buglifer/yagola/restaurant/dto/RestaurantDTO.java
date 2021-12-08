package com.buglifer.yagola.restaurant.dto;

import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.enums.restaurant.Category;
import com.buglifer.yagola.order.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO extends CommonDTO {

    private String name;
    private String apiID;
    private String tel;
    private String imgLink;
    private Category category;
    private List<OrderDTO> orders;

    public RestaurantDTO(RestaurantEntity restaurantEntity) {
        if(restaurantEntity == null) {
            return;
        }
        setSeq(restaurantEntity.getSeq());
        setCreatedTime(restaurantEntity.getCreatedTime());
        name = restaurantEntity.getName();
        apiID = restaurantEntity.getApiID();
        tel = restaurantEntity.getTel();
        imgLink = restaurantEntity.getImgLink();
        category = restaurantEntity.getCategory();
    }

    public void setOrder(OrderDTO orderDTO) {
        if(this.orders.isEmpty()) {
            orders = new LinkedList<>();
        }
        orders.add(orderDTO);
    }
}
