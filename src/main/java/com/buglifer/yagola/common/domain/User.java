package com.buglifer.yagola.common.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@EqualsAndHashCode(callSuper = true, of = "id")
@Table(name = "TB_USER")
@Entity
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_SEQ")
    private long seq;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "ip")
    private String ip;

}
