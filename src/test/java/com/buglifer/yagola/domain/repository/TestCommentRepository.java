package com.buglifer.yagola.domain.repository;

import com.buglifer.yagola.common.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCommentRepository extends JpaRepository<CommentEntity, Long> {
}
