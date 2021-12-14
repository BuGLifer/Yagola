package com.buglifer.yagola.menu.search;

import com.buglifer.yagola.common.domain.CommonEntity;
import com.buglifer.yagola.common.domain.MenuEntity;
import com.buglifer.yagola.common.spec.CommonSearch;
import com.buglifer.yagola.common.spec.SpecBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@ToString(callSuper = true)
public class MenuSearch extends CommonSearch {

    private long restSeq;

    @Override
    public Specification<MenuEntity> toSpec() {
        return SpecBuilder
                .builder(MenuEntity.class)
                .addSpec(Specification.where(MenuSpec.equalRestSeq(restSeq)))
                .toSpec();
    }
}
