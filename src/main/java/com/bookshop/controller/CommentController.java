package com.bookshop.controller;

import com.bookshop.DTO.Comment;
import com.bookshop.service.Impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/comment/{cId}")
    public Comment getCommentById(@PathVariable Integer cId) {
        return commentService.getCommentById(cId);
    }

    @GetMapping("/book/{bid}/comments")
    public List<Comment> getCommentsOfBook(@PathVariable Integer bid) {

        return commentService.getCommentsOfBook(bid);
    }

    @PutMapping("/comment/save/{cId}")
    public ResponseEntity<String> updateComment(@PathVariable Integer cId, @RequestBody Comment comment) {
        comment.setId(cId);
        commentService.updateComment(comment);
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/comment/save")
    public ResponseEntity<String> addComment(@RequestBody Comment comment) {

        commentService.addComment(comment);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/comment/delete/{cId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer cId) {
        commentService.deleteComment(cId);
        return ResponseEntity.ok().build();
    }
}
