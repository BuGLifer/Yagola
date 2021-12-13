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

    @Builder(
            builderClassName = "entity"
            , builderMethodName = "fromEntity"
    )
    private RestaurantDTO(RestaurantEntity entity) {
        if(entity == null) {
            return;
        }
        setSeq(entity.getSeq());
        setCreatedTime(entity.getCreatedTime());
        name = entity.getName();
        apiID = entity.getApiID();
        tel = entity.getTel();
        imgLink = entity.getImgLink();
        category = entity.getCategory();
        orders = entity.getOrders().stream().map(
                e -> OrderDTO
                    .fromEntity()
                    .entity(e)
                    .build()
        ).collect(Collectors.toList());
    }

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initRestaurant"
    )
    private RestaurantDTO(long seq) {
        setSeq(seq);
    }

    public void setOrder(OrderDTO orderDTO) {
        if(this.orders.isEmpty()) {
            orders = new LinkedList<>();
        }
        orders.add(orderDTO);
    }


}
