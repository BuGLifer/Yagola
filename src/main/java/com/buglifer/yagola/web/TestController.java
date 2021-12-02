package com.buglifer.yagola.web;

import com.buglifer.yagola.dto.TestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String hello(TestDTO testDTO) {
        return "tsss";
    }

    @GetMapping("/gillog")
    public String gillog() {
        return "gillog11";
    }

}
