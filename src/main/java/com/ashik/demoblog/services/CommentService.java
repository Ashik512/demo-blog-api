package com.ashik.demoblog.services;

import com.ashik.demoblog.payloads.ResponseDto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);

    List<CommentDto> getCommentsByPostId(Integer postId);

}
