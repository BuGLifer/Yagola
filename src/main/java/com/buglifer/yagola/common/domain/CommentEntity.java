package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.comment.dto.CommentDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(callSuper = true, of = "seq")
@Table(name = "TB_COMMENT")
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

    @Builder(builderMethodName = "initComment")
    public CommentEntity(CommentDTO dto) {
        seq = dto.getSeq();
        comment = dto.getComment();
        view = dto.isView();
        if(dto.getUser() != null) {
            user = UserEntity
                    .initUser()
                    .seq(dto.getUser().getSeq())
                    .build();

        }
        if(dto.getOrder() != null) {
            order = OrderEntity
                    .initOrder()
                    .seq(dto.getOrder().getSeq())
                    .build();
        }
        if(dto.getParentComment() != null) {
            pSeq = dto.getParentComment().getSeq();
        }
    }
}
