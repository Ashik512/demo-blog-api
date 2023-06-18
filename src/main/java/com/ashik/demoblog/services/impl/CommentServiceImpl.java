package com.ashik.demoblog.services.impl;

import com.ashik.demoblog.entities.Comment;
import com.ashik.demoblog.entities.Post;
import com.ashik.demoblog.exceptions.ResourceNotFoundException;
import com.ashik.demoblog.payloads.ResponseDto.CommentDto;
import com.ashik.demoblog.repositories.CommentRepo;
import com.ashik.demoblog.repositories.PostRepo;
import com.ashik.demoblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        Comment comment = convertToEntity(commentDto, post);

        Comment savedComment = commentRepo.save(comment);

        return convertToDto(savedComment);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

        commentRepo.delete(comment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        List<Comment> comments = commentRepo.findByPost(post);

        List<CommentDto> commentDtoList = comments.stream().map(comment -> convertToDto(comment)).collect(Collectors.toList());

        return commentDtoList;
    }

    public Comment convertToEntity(CommentDto commentDto, Post post) {

        Comment comment = new Comment();

        // comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setPost(post);

        return comment;

    }

    public CommentDto convertToDto(Comment comment) {

        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());

        return commentDto;

    }
}
