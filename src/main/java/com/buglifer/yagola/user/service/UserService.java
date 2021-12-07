package com.buglifer.yagola.user.service;

import com.buglifer.yagola.common.domain.UserEntity;
import com.buglifer.yagola.user.dto.UserDTO;
import com.buglifer.yagola.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public UserDTO getUserBySeq(long seq) {
        UserEntity userEntity = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findById(seq);
        if(optionalUserEntity.isPresent()) {
            userEntity = optionalUserEntity.get();
        }
        return new UserDTO(userEntity);
    }
}
