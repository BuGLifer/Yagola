package com.buglifer.yagola.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class WebController {

    @GetMapping("")
    public String getMain() {
        return "web/Main";
    }

    @GetMapping("invite")
    public String getInvite() { return "web/Invite"; }

    @GetMapping("gola")
    public String getGola() { return "web/Gola"; }

}
