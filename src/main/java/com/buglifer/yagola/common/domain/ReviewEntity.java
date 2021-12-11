package com.buglifer.yagola.common.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
@Table(name = "TB_REVIEW")
@Entity
public class ReviewEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RV_SEQ")
    private long seq;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "LIKE")
    private boolean like;

    @Column(name = "VIEW")
    private boolean view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "M_SEQ")
    private MenuEntity menu;

    @OneToOne(mappedBy = "review")
    private UserOrderEntity userOrder;

    public void setMenu(MenuEntity menuEntity) {
        if(menu != null) {
            menu.getReviews().remove(this);
        }
        menu = menuEntity;
        if(!menu.getReviews().contains(this)) {
            menu.getReviews().add(this);
        }
    }
}
