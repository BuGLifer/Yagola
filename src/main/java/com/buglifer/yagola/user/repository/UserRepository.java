package com.buglifer.yagola.user.repository;

import com.buglifer.yagola.common.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByNickName(String nickName);

}