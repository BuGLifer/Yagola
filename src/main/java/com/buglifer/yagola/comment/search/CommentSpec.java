package com.buglifer.yagola.comment.search;

import com.buglifer.yagola.common.domain.CommentEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CommentSpec {

    public static Specification<CommentEntity> isView(final boolean isView) {
        return new Specification<CommentEntity>() {
            @Override
            public Predicate toPredicate(Root<CommentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(isView) return criteriaBuilder.isTrue(root.get("view"));
                return criteriaBuilder.isFalse(root.get("view"));
            }
        };
    }
}
