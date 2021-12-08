package com.buglifer.yagola.common.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
@Entity
public class UserOrderEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UO_SEQ")
    private long seq;

    @Column(name = "HOST")
    private boolean host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_SEQ")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "O_SEQ")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "M_SEQ")
    private MenuEntity menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RV_SEQ")
    private ReviewEntity review;

    public void setUser(UserEntity userEntity) {
        user = userEntity;
        if(!user.getUserOrders().contains(this)) {
            user.getUserOrders().add(this);
        }
    }
}
