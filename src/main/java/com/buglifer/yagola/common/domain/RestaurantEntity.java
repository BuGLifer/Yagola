package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.common.enums.restaurant.Category;
import com.buglifer.yagola.common.enums.restaurant.SetCategoryConverter;
import com.buglifer.yagola.restaurant.dto.RestaurantDTO;
import lombok.*;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
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

    @Convert(converter = SetCategoryConverter.class)
    @Column(name = "CATEGORY")
    private EnumSet<Category> category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    private Date endTime;

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

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initRestaurant"
    )
    public RestaurantEntity(RestaurantDTO dto) {
        seq = dto.getSeq();
        name = dto.getName();
        apiID = dto.getApiID();
        tel = dto.getTel();
        imgLink = dto.getImgLink();
        category = dto.getCategory();
    }

    @Builder(
            builderClassName = "initRestSeq"
            , builderMethodName = "initRestSeq"
    )
    public RestaurantEntity(long seq) { this.seq = seq; }

}
