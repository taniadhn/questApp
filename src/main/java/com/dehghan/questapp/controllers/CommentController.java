package com.dehghan.questapp.controllers;


import com.dehghan.questapp.entities.Comment;
import com.dehghan.questapp.requests.CommentCreateRequest;
import com.dehghan.questapp.requests.CommentUpdateRequest;
import com.dehghan.questapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @GetMapping
    /**RequestParam: Request içinde parametre olarak bize gelecekler*/
    public List<Comment> getAllComments(@RequestParam Optional<Long > userId, @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId,postId);

    }

    @PostMapping
    public Comment getCreateOneComment(@RequestBody CommentCreateRequest createRequest){

        //createRequest Service yönlendireceğiz
        return commentService.createOneComment(createRequest);
    }

    @PutMapping("/{postId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){

        return commentService.updateComment(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public  void  DeleteOneComment(@PathVariable Long commentId){

        commentService.deleteOneComment(commentId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment( @PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

}
