package com.buglifer.yagola.menu.controller;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.menu.search.MenuSearch;
import com.buglifer.yagola.menu.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;
    // parameter로 restSeq가 있으면 해당 식당 메뉴 조회, 없으면 전체 식당 메뉴 조회
    @GetMapping
    public ResponseEntity<Page<MenuDTO>> getAllMenu(MenuSearch search) {
        return ResponseEntity.ok().body(menuService.findAllMenu(search));
    }

    // menu-seq 메뉴 상세 조회
    @GetMapping("{menu-seq}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable("menu-seq") long menuSeq) {
        return ResponseEntity.ok().body(menuService.findMenu(menuSeq));
    }

    // rest-seq 식당에 메뉴 추가
    @PostMapping
    public ResponseEntity<CommentDTO> postMenu(@RequestBody MenuDTO menuDTO) {
        return ResponseEntity.ok().body(menuService.saveMenu(menuDTO));
    }

    // menu-seq 메뉴 삭제
    @DeleteMapping("{menu-seq}")
    public ResponseEntity<Long> deleteMenu(@PathVariable("menu-seq") long menuSeq) {
        return ResponseEntity.ok().body(menuService.removeMenu(menuSeq));
    }
}
