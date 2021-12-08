package com.buglifer.yagola.common.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
@Table(name = "TB_USER")
@Entity
public class UserEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_SEQ")
    private long seq;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "ip")
    private String ip;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "user")
    private List<UserOrderEntity> userOrders;

    @Builder(builderMethodName = "initUser")
    public UserEntity(String nickName, String ip) {
        this.nickName = nickName;
        this.ip = ip;
    }

    public void addComment(CommentEntity commentEntity) {
        comments.add(commentEntity);
        if(commentEntity.getUser() != this) {
            commentEntity.setUser(this);
        }
    }

    public void addUserOrder(UserOrderEntity userOrderEntity) {
        userOrders.add(userOrderEntity);
        if(userOrderEntity.getUser() != this) {
            userOrderEntity.setUser(this);
        }
    }
}
