package com.buglifer.yagola.comment.dto;

import com.buglifer.yagola.common.domain.CommentEntity;
import com.buglifer.yagola.common.domain.UserEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.dto.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class CommentDTO extends PostDTO {

    private long pSeq;

    public CommentDTO(CommentEntity commentEntity) {
        setSeq(commentEntity.getSeq());
        pSeq = commentEntity.getPSeq();
        setView(commentEntity.isView());
        setCreatedTime(commentEntity.getCreatedTime());
        if(commentEntity.getComment() != null) {
            setComment(commentEntity.getComment());
        }
    }
}
