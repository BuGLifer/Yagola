package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.common.enums.restaurant.Category;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "TB_RESTAURANT")
@EqualsAndHashCode(callSuper = true, of = "seq")
@Entity
public class RestaurantEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_SEQ")
    private long seq;

    @Column(name = "NAME")
    private String name;

    @Column(name = "API_ID")
    private String apiID;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "IMG_LINK")
    private String imgLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private Category category;

    @OneToMany(mappedBy = "restaurant")
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuEntity> menus;

    public void addOrder(OrderEntity orderEntity) {
        orders.add(orderEntity);
        if(orderEntity.getRestaurant() != this) {
            orderEntity.setRestaurant(this);
        }
    }

    public void addMenu(MenuEntity menuEntity) {
        menus.add(menuEntity);
        if(menuEntity.getRestaurant() != this) {
            menuEntity.setRestaurant(this);
        }
    }
}
