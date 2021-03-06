package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@ToString(callSuper = true)
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_SEQ")
    private UserEntity user;

    @ToString.Exclude
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

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initComment"
    )
    private CommentEntity(CommentDTO dto) {
        seq = dto.getSeq();
        comment = dto.getComment();
        if(dto.getView() != null) view = dto.getView().booleanValue();
        if(dto.getUser() != null) {
            user = UserEntity
                    .initUser()
                    .seq(dto.getUser().getSeq())
                    .build();
        }
        if(dto.getOrder() != null) {
            order = OrderEntity
                    .initOrderSeq()
                    .seq(dto.getOrder().getSeq())
                    .build();
        }
        if(dto.getParentComment() != null) {
            pSeq = dto.getParentComment().getSeq();
        }
    }
}
