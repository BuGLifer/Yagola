package com.buglifer.yagola.test.repository;

import com.buglifer.yagola.test.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestUserRepository extends JpaRepository<TestUser, Long> {
    public Optional<TestUser> findByNickName(String nickName);
}
