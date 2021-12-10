package com.buglifer.yagola.common.spec;

import com.buglifer.yagola.common.domain.CommonEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@ToString
public class CommonSearch implements CommonSpec {

    private int page = 0;
    private int limit = 10;


    @Override
    public Pageable toPageable() {
        return PageRequest.of(page == 0 ? page : page -1, limit);
    }

    @Override
    public Specification<? extends CommonEntity> toSpec() {
        return null;
    }
}
