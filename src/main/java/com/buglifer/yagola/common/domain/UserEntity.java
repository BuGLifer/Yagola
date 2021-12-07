package com.buglifer.yagola.common.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
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

}
