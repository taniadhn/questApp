package com.dehghan.questapp.controllers;


import com.dehghan.questapp.entities.Like;
import com.dehghan.questapp.requests.LikeCreateRequest;
import com.dehghan.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService= likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){

        return likeService.getAllLikes(userId,postId);
    }
    @GetMapping("/{likeId}")
    public Like getOneLikeById(@PathVariable Long likeId){
        return likeService.getOneLieById(likeId);
    }

    @PostMapping
    public Like CreateOneLikeById(@RequestBody LikeCreateRequest request){

        return likeService.createOneLike(request);
    }

    @DeleteMapping("/{likeId}")
    public  void deleteOneLikeById(@PathVariable Long likeId){

        likeService.deleteOneLike(likeId);
    }
}
