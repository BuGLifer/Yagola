package com.buglifer.yagola.test.controller;

import com.buglifer.yagola.common.enums.restaurant.Category;
import com.buglifer.yagola.test.domain.TestDomain;
import com.buglifer.yagola.test.domain.TestUser;
import com.buglifer.yagola.test.dto.TestDTO;
import com.buglifer.yagola.test.repository.TestUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class TestController {

    private TestUserRepository testUserRepository;

    @GetMapping("/test")
    public String hello(TestDTO testDTO) {
        String result = "test";
        if(testDTO != null)
            result = testDTO.getTest();
        return Category.KOREA.getName();
    }

    @GetMapping("/gillog")
    public String gillog() {
        return "gillog";
    }

    @GetMapping("/jpa")
    public String jpa(TestDTO testDTO) {
        if (testDTO.getTest() == null) {
            return TestDomain.builder()
                    .test("test Domain")
                    .build()
                    .toString();
        }
        return TestDomain.builder()
                .test(testDTO.getTest())
                .build()
                .toString();
    }

    @GetMapping("/db")
    public String db(TestDTO testDTO) {
        if(testDTO.getTest() == null) {
            return "no";
        }
        Optional<TestUser> testUser = testUserRepository.findByNickName(testDTO.getTest());
        if(!testUser.isPresent()) {
            TestUser newUser = testUserRepository.save(TestUser.builder().nickName(testDTO.getTest()).build());
            return newUser.toString();
        }

        return testUser.get().toString();
    }
}
