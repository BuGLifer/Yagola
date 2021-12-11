package com.buglifer.yagola.user.dto;

import com.buglifer.yagola.common.dto.CommonDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class UserDTO extends CommonDTO {

    private String nickName;
    private String ip;

    @Builder(builderMethodName = "initUser")
    public UserDTO(long seq) {
        setSeq(seq);
    }
}
