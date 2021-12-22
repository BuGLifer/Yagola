package com.buglifer.yagola.user.dto;

import com.buglifer.yagola.common.domain.UserEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class UserDTO extends CommonDTO {

    private String nickName;
    private String ip;

    @Builder(
            builderClassName = "init"
            , builderMethodName = "initUser"
    )
    public UserDTO(long seq) {
        setSeq(seq);
    }

    @Builder(
            builderClassName = "entity"
            , builderMethodName = "fromEntity"
    )
    private UserDTO(UserEntity entity) {
        setSeq(entity.getSeq());
        setCreatedTime(entity.getCreatedTime());
        nickName = entity.getNickName();
        ip = entity.getIp();
    }
}
