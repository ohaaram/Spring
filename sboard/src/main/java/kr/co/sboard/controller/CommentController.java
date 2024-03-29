package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{no}")
    public ResponseEntity<List<ArticleDTO>> comment(@PathVariable("no") int no){

        return commentService.selectComments(no);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> comment(@RequestBody ArticleDTO articleDTO, HttpServletRequest req){

        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);
        log.info("articleDTO : " + articleDTO);

        return commentService.insertComment(articleDTO);
    }



    @PostMapping("/comment/delete")
    public void DeleteComment(@RequestBody ArticleDTO articleDTO){

        log.info("지금 들어오는 값이 pk : ?"+articleDTO.getNo());

        commentService.deleteComment(articleDTO.getNo());

    }

    @PostMapping("/comment/modify")
    public ResponseEntity<?> ModifyComment(@RequestBody ArticleDTO articleDTO,HttpServletRequest req){
        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);
        articleDTO.setRdate(LocalDateTime.now());

        log.info("여기는 comment-modify야");

        return commentService.modifyComment(articleDTO);

    }
}
