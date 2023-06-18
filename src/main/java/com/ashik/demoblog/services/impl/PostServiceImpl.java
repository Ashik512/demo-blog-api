package com.ashik.demoblog.services.impl;

import com.ashik.demoblog.entities.Category;
import com.ashik.demoblog.entities.Post;
import com.ashik.demoblog.entities.User;
import com.ashik.demoblog.exceptions.ResourceNotFoundException;
import com.ashik.demoblog.payloads.RequestDto.PostRequestDto;
import com.ashik.demoblog.payloads.ResponseDto.CatDto;
import com.ashik.demoblog.payloads.ResponseDto.PostResponse;
import com.ashik.demoblog.payloads.ResponseDto.PostResponseDto;
import com.ashik.demoblog.payloads.ResponseDto.UserOnPostDto;
import com.ashik.demoblog.repositories.CategoryRepo;
import com.ashik.demoblog.repositories.PostRepo;
import com.ashik.demoblog.repositories.UserRepo;
import com.ashik.demoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto, Integer userId, Integer categoryId) {

        Post post = convertToEntity(postRequestDto, userId, categoryId);

        Post savedPost = postRepo.save(post);

        return convertToResponseDto(savedPost);
    }

    @Override
    public PostResponseDto updatePost(Integer postId, PostRequestDto postRequestDto) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());

        Post updatedPost = postRepo.save(post);

        return convertToResponseDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        postRepo.delete(post);
    }


    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(pageable);

        List<Post> posts = pagePost.getContent();
        List<PostResponseDto> postResponseDtos = posts.stream().map((post) -> convertToResponseDto(post)).collect(Collectors.toList());

        return convertToPostResponse(postResponseDtos, pagePost);
    }

    @Override
    public PostResponseDto getSinglePost(Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        return convertToResponseDto(post);
    }

    @Override
    public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Post> pagePost = postRepo.findByCategory(category, pageable);
        List<Post> posts = pagePost.getContent();

        List<PostResponseDto> postResponseDto = posts.stream().map((post) -> convertToResponseDto(post)).collect(Collectors.toList());

        return convertToPostResponse(postResponseDto, pagePost);

    }

    @Override
    public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Post> pagePost = postRepo.findByUser(user, pageable);
        List<Post> posts = pagePost.getContent();

        List<PostResponseDto> postResponseDto = posts.stream().map((post) -> convertToResponseDto(post)).collect(Collectors.toList());

        return convertToPostResponse(postResponseDto, pagePost);
    }

    @Override
    public PostResponse searchPostByTitle(String keyword, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber -1, pageSize);
        Page<Post> pagePosts = postRepo.findByTitleContaining(keyword, pageable);
        List<Post> posts = pagePosts.getContent();

        List<PostResponseDto> postResponseDtos = posts.stream().map(post -> convertToResponseDto(post)).collect(Collectors.toList());

        return convertToPostResponse(postResponseDtos, pagePosts);
    }

    private Post convertToEntity(PostRequestDto postRequestDto, Integer userId, Integer categoryId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = new Post();

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        return post;
    }

    private PostResponseDto convertToResponseDto(Post post) {

        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setPostId(post.getPostId());
        postResponseDto.setTittle(post.getTitle());
        postResponseDto.setContent(post.getContent());
        postResponseDto.setImageName(post.getImageName());

        CatDto catDto = new CatDto();
        catDto.setCategoryId(post.getCategory().getCategoryId());
        catDto.setCategoryTitle(post.getCategory().getCategoryTitle());

        UserOnPostDto userOnPostDto = new UserOnPostDto();
        userOnPostDto.setUserId(post.getUser().getId());
        userOnPostDto.setName(post.getUser().getName());

        postResponseDto.setCatDto(catDto);
        postResponseDto.setUserOnPostDto(userOnPostDto);

        return postResponseDto;
    }

    private PostResponse convertToPostResponse(List<PostResponseDto> postResponseDto, Page<Post> pagePost) {

        PostResponse postResponse = new PostResponse();

        postResponse.setModel(postResponseDto);
        postResponse.setPageNumber(pagePost.getNumber() + 1);
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setIsLast(pagePost.isLast());

        return postResponse;
    }


}
