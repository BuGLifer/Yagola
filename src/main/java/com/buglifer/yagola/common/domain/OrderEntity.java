package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.common.enums.order.Status;
import com.buglifer.yagola.order.dto.OrderDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
@Table(name = "TB_ORDER")
@Entity
public class OrderEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "O_SEQ")
    private long seq;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "OFFLINE_TIME")
    private Date offlineTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "ORDER_TIME")
    private Date orderTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "ARRIVAL_TIME")
    private Date arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "R_SEQ")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "order")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "order")
    private List<UserOrderEntity> userOrders;

    public void setRestaurant(RestaurantEntity restaurantEntity) {
        if(restaurant != null) {
            restaurant.getOrders().remove(this);
        }
        restaurant = restaurantEntity;
        if(!restaurant.getOrders().contains(this)) {
            restaurant.getOrders().add(this);
        }
    }

    public void addComment(CommentEntity commentEntity) {
        comments.add(commentEntity);
        if(commentEntity.getOrder() != this) {
            commentEntity.setOrder(this);
        }
    }

    public void addUser(UserOrderEntity userOrderEntity) {
        userOrders.add(userOrderEntity);
        if(userOrderEntity.getOrder() != this) {
            userOrderEntity.setOrder(this);
        }
    }

    @Builder(
            builderClassName = "initOrder"
            , builderMethodName = "initOrder"
    )
    public OrderEntity(long seq) {
        this.seq = seq;
    }

    @Builder(
            builderClassName = "saveOrder"
            , builderMethodName = "saveOrder"
    )
    public OrderEntity(OrderDTO dto) {
        seq = dto.getSeq();
        status = Status.ONLINE;
        if (dto.getRestaurant() != null) {
            restaurant = RestaurantEntity
                    .initRestSeq()
                    .seq(dto.getRestaurant().getSeq())
                    .build();
        }
    }
}
