package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.menu.dto.MenuDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "DESCRIPTION")
    private String description;

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

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initMenu"
    )
    private MenuEntity(long seq) {
        this.seq = seq;
    }

    @Builder(
            builderClassName = "dto"
            , builderMethodName = "fromDTO"
    )
    public MenuEntity(MenuDTO dto) {
        seq = dto.getSeq();
        name = dto.getName();
        price = dto.getPrice();
        imgLink = dto.getImgLink();
        if (dto.getRestaurant() != null) {
            restaurant = RestaurantEntity
                    .initRestSeq()
                    .seq(dto.getRestaurant().getSeq())
                    .build();
        }
    }
}
