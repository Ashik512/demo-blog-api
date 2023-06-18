package com.ashik.demoblog.services;

import com.ashik.demoblog.payloads.RequestDto.PostRequestDto;
import com.ashik.demoblog.payloads.ResponseDto.PostResponse;
import com.ashik.demoblog.payloads.ResponseDto.PostResponseDto;

import java.util.List;

public interface PostService {

   PostResponseDto createPost(PostRequestDto postRequestDto, Integer userId, Integer categoryId);

   PostResponseDto updatePost(Integer postId, PostRequestDto postRequestDto);

   void deletePost(Integer postId);

   PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

   PostResponseDto getSinglePost(Integer postId);

   PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

   PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

   PostResponse searchPostByTitle(String keyword, Integer pageNumber, Integer pageSize);


}
