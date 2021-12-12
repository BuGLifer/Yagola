package com.buglifer.yagola.common.spec;

import com.buglifer.yagola.common.domain.CommonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CommonSpec {

    public Pageable toPageable();

    public Specification<? extends CommonEntity> toSpec();

}
