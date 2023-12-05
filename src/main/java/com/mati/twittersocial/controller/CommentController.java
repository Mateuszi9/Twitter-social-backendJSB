package com.mati.twittersocial.controller;

import com.mati.twittersocial.model.Comment;
import com.mati.twittersocial.model.User;
import com.mati.twittersocial.service.CommentService;
import com.mati.twittersocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    private Comment createComment(@RequestBody Comment comment,
                                  @RequestHeader("Authorization") String jwt,
                                  @PathVariable("postId") Integer postId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment createdComment = commentService.
                createComment(comment,
                        postId, user.getId());


        return createdComment;
    }

    @PostMapping("/api/comments/like/{commentId}")
    private Comment likeComment(
                                  @RequestHeader("Authorization") String jwt,
                                  @PathVariable("commentId") Integer commentId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment likedComment = commentService.
                likeComment(commentId,
                        user.getId());


        return likedComment;
    }


}
