package com.buglifer.yagola.common.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
@Entity
public class CommentEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_SEQ")
    private long seq;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "P_SEQ")
    private long pSeq;

    @Column(name = "VIEW")
    private boolean view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_SEQ")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "O_SEQ")
    private OrderEntity order;

    public void setUser(UserEntity userEntity) {
        if(user != null) {
            user.getComments().remove(this);
        }
        user = userEntity;
        if(!user.getComments().contains(this)) {
            user.getComments().add(this);
        }
    }

    public void setOrder(OrderEntity orderEntity) {
        if(order != null) {
            order.getComments().remove(this);
        }
        order = orderEntity;
        if(!order.getComments().contains(this)) {
            order.getComments().add(this);
        }
    }
}
