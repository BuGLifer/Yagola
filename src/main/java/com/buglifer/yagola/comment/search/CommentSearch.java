package com.buglifer.yagola.comment.search;

import com.buglifer.yagola.common.domain.CommentEntity;
import com.buglifer.yagola.common.spec.CommonSearch;
import com.buglifer.yagola.common.spec.SpecBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@ToString(callSuper = true)
public class CommentSearch extends CommonSearch {

    private boolean view = true;

    @Override
    public Specification<CommentEntity> toSpec() {
        return SpecBuilder
                .builder(CommentEntity.class)
                .addSpec(Specification.where(CommentSpec.isView(view)))
                .toSpec();
    }
}
