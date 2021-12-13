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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class CommentDTO extends PostDTO {

    private CommentDTO parentComment;
    private UserDTO user;
    private OrderDTO order;

    @Builder(
            builderClassName = "entity"
            , builderMethodName = "fromEntity"
    )
    private CommentDTO(CommentEntity entity) {
        setSeq(entity.getSeq());
        parentComment = CommentDTO
                .initComment()
                .seq(entity.getPSeq())
                .build();
        setView(entity.isView());
        setCreatedTime(entity.getCreatedTime());
        if(entity.getComment() != null) {
            setComment(entity.getComment());
        }
    }

    @Builder(
            builderClassName = "dto"
            , builderMethodName = "initComment"
    )
    private CommentDTO(long seq) {
        setSeq(seq);
    }

}
