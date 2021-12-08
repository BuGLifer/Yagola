package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.common.enums.order.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
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

    public void setRestaurant(RestaurantEntity restaurantEntity) {
        if(this.restaurant != null) {
            this.restaurant.getOrders().remove(this);
        }
        this.restaurant = restaurantEntity;
        if(!restaurantEntity.getOrders().contains(this)) {
            restaurantEntity.getOrders().add(this);
        }
    }
}
