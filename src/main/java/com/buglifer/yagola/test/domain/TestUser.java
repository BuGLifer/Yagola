package com.buglifer.yagola.test.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Table(name = "TB_USER")
@Entity
public class TestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_SEQ")
    private long id;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "IP")
    private String ip;

    @Builder
    public TestUser(String nickName, String ip) {
        this.nickName = nickName;
        this.ip = ip;
    }
}
