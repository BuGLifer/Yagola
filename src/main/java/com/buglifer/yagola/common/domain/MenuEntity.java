package com.buglifer.yagola.common.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
@Table(name = "TB_MENU")
@Entity
public class MenuEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_SEQ")
    private long seq;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "IMG_LINK")
    private String imgLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "R_SEQ")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "menu")
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "menu")
    private List<UserOrderEntity> userOrders;

    public void setRestaurant(RestaurantEntity restaurantEntity) {
        if(restaurant != null) {
            restaurant.getMenus().remove(this);
        }
        restaurant = restaurantEntity;
        if(!restaurant.getMenus().contains(this)) {
            restaurant.getMenus().add(this);
        }
    }

    public void addReview(ReviewEntity reviewEntity) {
        reviews.add(reviewEntity);
        if(reviewEntity.getMenu() != this) {
            reviewEntity.setMenu(this);
        }
    }

    public void addUserOrder(UserOrderEntity userOrderEntity) {
        userOrders.add(userOrderEntity);
        if(userOrderEntity.getMenu() != this) {
            userOrderEntity.setMenu(this);
        }
    }
}
