package com.buglifer.yagola.menu.controller;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.menu.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;
    // 모든 식당의 모든 메뉴 and 한 식당의 모든 메뉴 조회
    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenu(@RequestParam(value = "rest-seq", required = false, defaultValue = "0") String restSeq) {
        return ResponseEntity.ok().body(menuService.getAllMenu(Long.valueOf(restSeq)));
    }

    // seq번 메뉴 상세 조회
//    @GetMapping("{menu-seq}")
//    public ResponseEntity<MenuDTO> getMenu(@PathVariable("menu-seq") int menuSeq) {
//        return menuSeq;
//    }

//    @PostMapping("{rest-seq}")
//    public ResponseEntity<CommentDTO> postMenu(@PathVariable("rest-seq") int restSeq, @RequestBody MenuDTO menuDTO) {
//
//    }

}
