package com.buglifer.yagola.comment.service;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.comment.repository.CommentRepository;
import com.buglifer.yagola.comment.search.CommentSearch;
import com.buglifer.yagola.common.domain.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;
import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.rmi.AccessException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final EntityManager entityManager;
    private final CommentRepository commentRepository;

    public Page<CommentDTO> findCommentBySearch(CommentSearch search) {
        Page<CommentEntity> commentEntityList =  commentRepository.findAll(search.toSpec(), search.toPageable());
        return commentEntityList
                .map(
                        e -> CommentDTO
                                .fromEntity()
                                .entity(e)
                                .build()
                );
    }

    public CommentDTO findCommentBySeq(long seq) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(seq);
        if(!optionalCommentEntity.isPresent()) throw new EntityNotFoundException("해당 댓글이 존재하지 않습니다.");
        return CommentDTO
                .fromEntity()
                .entity(optionalCommentEntity.get())
                .build();
    }

    public CommentDTO saveComment(CommentDTO commentDTO) {
        CommentEntity commentEntity = CommentEntity
                .initComment()
                .dto(commentDTO)
                .build();
        commentRepository.save(commentEntity);
        return CommentDTO
                .fromEntity()
                .entity(commentEntity)
                .build();
    }

    @Transactional
    public CommentDTO updateComment(CommentDTO commentDTO) {
        Optional<CommentEntity> optionalOldCommentEntity = commentRepository.findById(commentDTO.getSeq());
        if(!optionalOldCommentEntity.isPresent()) throw new EntityNotFoundException("해당 댓글이 존재하지 않습니다.");
        CommentDTO oldComment = CommentDTO
                                .fromEntity()
                                .entity(optionalOldCommentEntity.get())
                                .build();
        if(commentDTO.getComment() != null) oldComment.setComment(commentDTO.getComment());
        if(commentDTO.getView() != null) oldComment.setView(commentDTO.getView().booleanValue());
        CommentEntity newCommentEntity = CommentEntity
                                        .initComment()
                                        .dto(oldComment)
                                        .build();
        commentRepository.save(newCommentEntity);
        return CommentDTO
                .fromEntity()
                .entity(newCommentEntity)
                .build();
    }

    @Transactional
    public void deleteComment(CommentDTO commentDTO) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(commentDTO.getSeq());
        if(!optionalCommentEntity.isPresent()) throw new EntityNotFoundException("해당 댓글이 존재하지 않습니다.");
        if(optionalCommentEntity.get().getUser().getSeq() != commentDTO.getUser().getSeq()) throw new SecurityException("댓글 작성자만 접근 가능합니다.");
        commentRepository.delete(optionalCommentEntity.get());
    }
}
