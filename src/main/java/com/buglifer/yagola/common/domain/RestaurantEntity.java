package com.buglifer.yagola.common.domain;

import com.buglifer.yagola.common.enums.restaurant.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = "seq")
@Entity
public class RestaurantEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_SEQ")
    private long seq;

    @Column(name = "NAME")
    private String name;

    @Column(name = "API_ID")
    private String apiID;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "IMG_LINK")
    private String imgLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private Category category;

}
