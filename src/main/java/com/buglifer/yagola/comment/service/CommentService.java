package com.buglifer.yagola.comment.service;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.comment.repository.CommentRepository;
import com.buglifer.yagola.comment.search.CommentSearch;
import com.buglifer.yagola.common.domain.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Page<CommentDTO> findComments(CommentSearch search) {
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
        if(optionalCommentEntity.isPresent()) {
            return CommentDTO
                    .fromEntity()
                    .entity(optionalCommentEntity.get())
                    .build();
        }
        return CommentDTO.initComment().build();
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
}
