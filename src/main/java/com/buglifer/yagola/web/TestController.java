package com.buglifer.yagola.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String hello() {
        return "testtt";
    }

    @GetMapping("/gillog")
    public String gillog() {
        return "gillog11";
    }
}
