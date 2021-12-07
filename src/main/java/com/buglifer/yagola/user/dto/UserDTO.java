package com.buglifer.yagola.user.dto;

import com.buglifer.yagola.common.domain.UserEntity;
import com.buglifer.yagola.common.dto.CommonDTO;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class UserDTO extends CommonDTO {

    private String nickName;
    private String ip;

    public UserDTO(UserEntity userEntity) {
        if(userEntity == null) {
            return;
        }
        setSeq(userEntity.getSeq());
        if(userEntity.getNickName() != null) {
            nickName = userEntity.getNickName();
        }
        if(userEntity.getIp() != null) {
            ip = userEntity.getIp();
        }
    }

}
