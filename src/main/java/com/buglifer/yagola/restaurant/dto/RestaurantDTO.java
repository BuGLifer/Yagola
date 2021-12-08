package com.buglifer.yagola.restaurant.dto;

import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.enums.restaurant.Category;
import com.buglifer.yagola.order.dto.OrderDTO;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
@NoArgsConstructor
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
        orders = restaurantEntity.getOrders().stream().map(e -> new OrderDTO(e)).collect(Collectors.toList());
    }

    @Builder
    public RestaurantDTO(String name, String apiID, String tel, String imgLink, Category category) {
        this.name = name;
        this.apiID = apiID;
        this.tel = tel;
        this.imgLink = imgLink;
        this.category = category;
    }

    public void setOrder(OrderDTO orderDTO) {
        if(this.orders.isEmpty()) {
            orders = new LinkedList<>();
        }
        orders.add(orderDTO);
    }
}