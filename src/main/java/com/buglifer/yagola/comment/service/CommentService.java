package com.buglifer.yagola.comment.service;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.comment.repository.CommentRepository;
import com.buglifer.yagola.common.domain.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentDTO getCommentDTO(long seq) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(seq);
        if(optionalCommentEntity.isPresent()) {
            return new CommentDTO(optionalCommentEntity.get());
        }
        return new CommentDTO();
    }

    public CommentDTO saveComment(CommentDTO commentDTO) {
        CommentEntity commentEntity = CommentEntity
                .initComment()
                .commentDTO(commentDTO)
                .build();
        commentRepository.save(commentEntity);
        return new CommentDTO(commentEntity);
    }
}
