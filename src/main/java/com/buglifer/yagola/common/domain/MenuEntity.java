package com.buglifer.yagola.common.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
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

    public void setRestaurant(RestaurantEntity restaurantEntity) {
        if(restaurant != null) {
            restaurant.getMenus().remove(this);
        }
        restaurant = restaurantEntity;
        if(!restaurant.getMenus().contains(this)) {
            restaurant.getMenus().add(this);
        }
    }

}
