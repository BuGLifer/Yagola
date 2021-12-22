package com.buglifer.yagola.menu.controller;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.menu.dto.MenuDTO;
import com.buglifer.yagola.menu.search.MenuSearch;
import com.buglifer.yagola.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;
    // parameter로 restSeq가 있으면 해당 식당 메뉴 조회, 없으면 전체 식당 메뉴 조회
    @GetMapping
    public ResponseEntity<Page<MenuDTO>> getAllMenu(MenuSearch search) {
        return ResponseEntity.ok().body(menuService.findMenuBySearch(search));
    }

    // menu-seq 메뉴 상세 조회
    @GetMapping("{seq}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable("seq") long seq) {
        return ResponseEntity.ok().body(menuService.findMenuBySeq(seq));
    }

    // rest-seq 식당에 메뉴 추가
    @PostMapping
    public ResponseEntity<MenuDTO> postMenu(@RequestBody MenuDTO menuDTO) {
        return ResponseEntity.ok().body(menuService.saveMenu(menuDTO));
    }

    // menu-seq 메뉴 삭제
    @DeleteMapping("{seq}")
    public ResponseEntity<Long> deleteMenu(@PathVariable("seq") long seq) {
        menuService.removeMenu(seq);
        return ResponseEntity.noContent().build();
    }
}
