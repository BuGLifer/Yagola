package com.buglifer.yagola.menu.search;

import com.buglifer.yagola.common.domain.MenuEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MenuSpec {

    public static Specification<MenuEntity> equalRestSeq(long restSeq) {
        return new Specification<MenuEntity>() {
            @Override
            public Predicate toPredicate(Root<MenuEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("restaurant"), restSeq);
            }
        };
    }
}
