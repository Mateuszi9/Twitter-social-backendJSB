package com.mati.twittersocial.controller;

import com.mati.twittersocial.model.Post;
import com.mati.twittersocial.response.ApiResponse;
import com.mati.twittersocial.service.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable("userId") Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {

        String message = postService.deletePost(postId, userId);
        ApiResponse res = new ApiResponse(message, true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable("postId") Integer postId) throws Exception {
        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
        List<Post> posts=postService.findPostByUserId(userId);

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts(){
        List<Post> posts=postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {
        Post post = postService.savedPost(postId, userId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {
        Post post = postService.likePost(postId, userId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }



}
