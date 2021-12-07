package com.buglifer.yagola.user.controller;

import com.buglifer.yagola.user.dto.UserDTO;
import com.buglifer.yagola.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("users")
@RestController
public class UserController {

    private UserService userService;

    @GetMapping("{seq}")
    public UserDTO getUserDTO(@PathVariable(name = "seq", required = true) long seq) {
        return userService.getUserBySeq(seq);
    }
}
