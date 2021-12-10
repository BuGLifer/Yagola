package com.buglifer.yagola.comment.controller;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.comment.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{seq}")
    public ResponseEntity<CommentDTO> getCommentBySeq(@PathVariable(name = "seq") long seq) {
        return ResponseEntity.ok().body(commentService.findCommentBySeq(seq));
    }

    @PostMapping("")
    public ResponseEntity<CommentDTO> postCommentDTO(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok().body(commentService.saveComment(commentDTO));
    }
}
