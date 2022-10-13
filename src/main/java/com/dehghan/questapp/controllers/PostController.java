package com.dehghan.questapp.controllers;


import com.dehghan.questapp.entities.Post;
import com.dehghan.questapp.requests.PostCreatRequest;
import com.dehghan.questapp.requests.PostUpdateRequest;
import com.dehghan.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService){

        this.postService = postService;
    }


    @GetMapping
    /***
     * RequestParam Bize gelen Request içindeki parametreleri pars et ve bu sağımda bulunan değişkenin içerisine at
     * Optional olduğu için gelebilirde gelmeyebilirde. Geldiği durumda userId göre postları getirecem. gelmediğinde tüm postları getirecem
      */
    public List<Post> getAllPosts(@RequestParam Optional <Long> userId){

        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreatRequest newPostCreatRequest){

        return postService.createOnePost(newPostCreatRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdate){

        return postService.updatePost(postId, postUpdate);
    }

    @DeleteMapping("/{deleteId}")
    public void deleteOnePost(@PathVariable Long postId){

        postService.deleteOnePost(postId);

    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){

        return postService.getOnePostById(postId);
    }


}
