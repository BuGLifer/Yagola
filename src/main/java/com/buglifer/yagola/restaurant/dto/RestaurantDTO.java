package com.buglifer.yagola.restaurant.dto;

import com.buglifer.yagola.common.batch.response.yogiyo.RestaurantResponse;
import com.buglifer.yagola.common.domain.RestaurantEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.enums.restaurant.Category;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.order.dto.OrderDTO;
import lombok.*;

import java.util.*;
import java.util.function.Predicate;
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
    private EnumSet<Category> category;
    private Date startTime;
    private Date endTime;
    private List<OrderDTO> orders;
    private List<MenuDTO> menus;

    public void setOrder(OrderDTO orderDTO) {
        if(this.orders.isEmpty()) {
            orders = new LinkedList<>();
        }
        orders.add(orderDTO);
    }

    private void addCategoryInYogiyoResponse(String[] categories) {
        if(categories.length == 0) return;
        Arrays.stream(Category.values())
                .filter(
                        e -> Arrays.stream(categories).anyMatch(Predicate.isEqual(e.getName()))
                )
                .forEach(category::add);
    }

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
        startTime = entity.getStartTime();
        endTime = entity.getEndTime();
        if(entity.getOrders() != null) {
            orders = entity.getOrders().stream().map(
                    e -> OrderDTO
                            .fromEntity()
                            .entity(e)
                            .build()
            ).collect(Collectors.toList());
        }
        if(entity.getMenus() != null) {
            menus = entity.getMenus().stream().map(
                    e -> MenuDTO
                            .fromEntity()
                            .entity(e)
                            .build()
            ).collect(Collectors.toList());
        }
    }

    @Builder
    public RestaurantDTO(long seq, String name, String apiID, String tel, String imgLink, EnumSet<Category> category) {
        setSeq(seq);
        this.name = name;
        this.apiID = apiID;
        this.tel = tel;
        this.imgLink = imgLink;
        this.category = category;
    }

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initRestaurant"
    )
    private RestaurantDTO(long seq) {
        setSeq(seq);
    }

    @Builder(
            builderClassName = "api"
            , builderMethodName = "fromResponse"
    )
    private RestaurantDTO(RestaurantResponse response) {
        apiID = Long.toString(response.getId());
        name = response.getName();
        imgLink = response.getLogo_url();
        category = EnumSet.noneOf(Category.class);
        if(response.getCategories().length != 0) addCategoryInYogiyoResponse(response.getCategories());
    }

}
