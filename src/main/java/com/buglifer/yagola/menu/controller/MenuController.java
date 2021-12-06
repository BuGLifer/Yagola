package com.buglifer.yagola.menu.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {

    @GetMapping
    public String getAllMenu(@RequestParam(value = "rest-seq", required = false) String restSeq) {
        return restSeq==null ? "all menu" : "restaurant" + restSeq + " all menu";
    }

    @GetMapping("/{menuID}")
    public int getMenu(@PathVariable("menuID") int menuID) {
        return menuID;
    }

}
