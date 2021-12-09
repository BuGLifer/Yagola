package com.buglifer.yagola.comment.dto;

import com.buglifer.yagola.common.domain.CommentEntity;
import com.buglifer.yagola.common.domain.UserEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import com.buglifer.yagola.common.dto.PostDTO;
import com.buglifer.yagola.order.dto.OrderDTO;
import com.buglifer.yagola.user.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class CommentDTO extends PostDTO {

    private CommentDTO parentComment;
    private UserDTO user;
    private OrderDTO order;

    public CommentDTO(CommentEntity commentEntity) {
        setSeq(commentEntity.getSeq());
        parentComment = CommentDTO
                .initComment()
                .seq(commentEntity.getPSeq())
                .build();
        setView(commentEntity.isView());
        setCreatedTime(commentEntity.getCreatedTime());
        if(commentEntity.getComment() != null) {
            setComment(commentEntity.getComment());
        }
    }

    @Builder(builderMethodName = "initComment")
    public CommentDTO(long seq) {
        setSeq(seq);
    }

}
