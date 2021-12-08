package com.buglifer.yagola.comment.controller;

import com.buglifer.yagola.comment.dto.CommentDTO;
import com.buglifer.yagola.comment.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{seq}")
    public ResponseEntity<CommentDTO> getCommentBySeq(@PathVariable(name = "seq") long seq) {
        return ResponseEntity.ok().body(commentService.getCommentDTO(seq));
    }

}
