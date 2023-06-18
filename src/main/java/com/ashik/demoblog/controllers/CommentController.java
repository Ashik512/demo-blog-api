package com.ashik.demoblog.controllers;

import com.ashik.demoblog.payloads.ApiResponse;
import com.ashik.demoblog.payloads.ResponseDto.CommentDto;
import com.ashik.demoblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // create comment
    @PostMapping("/comment/post/{postId}")
    public ResponseEntity<?> create(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer postId) {

        CommentDto comment = commentService.createComment(commentDto, postId);

        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }

    // delete comment
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Integer commentId) {

        commentService.deleteComment(commentId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully", true), HttpStatus.OK);
    }

    // getting all comment of a post
    @GetMapping("/comments/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Integer postId) {

        List<CommentDto> commentDtoList = commentService.getCommentsByPostId(postId);

        return new ResponseEntity<List<CommentDto>>(commentDtoList, HttpStatus.OK);
    }

}
