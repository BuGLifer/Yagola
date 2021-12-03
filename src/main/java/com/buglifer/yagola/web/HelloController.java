package com.buglifer.yagola.web;

import com.buglifer.yagola.domain.TestDomain;
import com.buglifer.yagola.dto.TestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/test")
    public String hello(TestDTO testDTO) {
        String result = "test";
        if(testDTO != null)
            result = testDTO.getTest();
        return result;
    }

    @GetMapping("/gillog")
    public String gillog() {
        return "gillog";
    }

    @GetMapping("/jpa")
    public String jpa(TestDTO testDTO) {
        if(testDTO.getTest() == null) {
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
}
