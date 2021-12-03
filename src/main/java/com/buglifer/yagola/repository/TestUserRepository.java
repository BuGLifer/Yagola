package com.buglifer.yagola.repository;

import com.buglifer.yagola.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestUserRepository extends JpaRepository<TestUser, Long> {
    public Optional<TestUser> findByNickName(String nickName);
}
