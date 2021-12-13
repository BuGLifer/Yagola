package com.buglifer.yagola.user.controller;

import com.buglifer.yagola.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("users")
@RestController
public class UserController {

    @PostMapping("")
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO user) {
        return null;
    }

}
