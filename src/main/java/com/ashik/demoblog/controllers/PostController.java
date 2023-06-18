package com.ashik.demoblog.controllers;

import com.ashik.demoblog.config.AppConstants;
import com.ashik.demoblog.payloads.ApiResponse;
import com.ashik.demoblog.payloads.RequestDto.PostRequestDto;
import com.ashik.demoblog.payloads.ResponseDto.PostResponse;
import com.ashik.demoblog.payloads.ResponseDto.PostResponseDto;
import com.ashik.demoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // creating new post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto postRequestDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        PostResponseDto createdPost = postService.createPost(postRequestDto, userId, categoryId);

        return new ResponseEntity<PostResponseDto> (createdPost, HttpStatus.CREATED);
    }

    // updating post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Integer postId, @Valid @RequestBody PostRequestDto postRequestDto) {

        PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto);

        return new ResponseEntity<PostResponseDto>(postResponseDto, HttpStatus.OK);
    }

    // deleting post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {

        postService.deletePost(postId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }

    // getting all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts (
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
            ) {

         PostResponse allPost = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

         return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
    }

    // getting single posts
    @GetMapping("/posts/{postId}")
    public PostResponseDto getSinglePost(@PathVariable Integer postId) {

        PostResponseDto singlePost = postService.getSinglePost(postId);

        return singlePost;
    }

    //getting post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                                    @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
                                                                    ) {

        PostResponse postByCategory = postService.getPostByCategory(categoryId, pageNumber, pageSize);

        return new ResponseEntity<PostResponse>(postByCategory,HttpStatus.OK);
    }

    //getting post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                                @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
                                                                ) {

        PostResponse postByUser = postService.getPostByUser(userId, pageNumber, pageSize);

        return new ResponseEntity<PostResponse>(postByUser,HttpStatus.OK);
    }

    // search post
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<PostResponse> searchPostsByTitle(@PathVariable String keyword,
                                                                    @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
                                                                    ) {

        PostResponse postResponse = postService.searchPostByTitle(keyword, pageNumber, pageSize);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }


}
