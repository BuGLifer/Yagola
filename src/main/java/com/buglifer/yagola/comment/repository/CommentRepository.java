package com.buglifer.yagola.comment.repository;

import com.buglifer.yagola.common.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>, JpaSpecificationExecutor<CommentEntity> {
}
