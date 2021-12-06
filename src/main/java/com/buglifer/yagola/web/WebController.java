package com.buglifer.yagola.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class WebController {

    @GetMapping("/main")
    public String getMain() {
        return "web/Main";
    }

}
