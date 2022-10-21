package com.dehghan.questapp.services;


import com.dehghan.questapp.entities.Like;
import com.dehghan.questapp.entities.Post;
import com.dehghan.questapp.entities.User;
import com.dehghan.questapp.repos.LikeRepository;
import com.dehghan.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private PostService postService;
    private UserService userService;

    public LikeService(LikeRepository likeRepository, PostService postService, UserService userService){
        this.likeRepository= likeRepository;
        this.postService = postService;
        this.userService = userService;
    }


    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {

        if(userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId,postId);
        }else if(userId.isPresent()){
            return likeRepository.findByUserId(userId);
        } else if(postId.isPresent()){
            return likeRepository.findByPostId(postId);
        }else
            return  null;
    }

    public Like getOneLieById(Long likeId) {

        return likeRepository.findByLikeId(likeId);
    }

    public Like createOneLike(LikeCreateRequest request) {

        Post post = postService.getOnePostById(request.getPostId());
        User user = userService.getOneUserById(request.getUserId());

        if( post != null && user !=null){

            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }

    public void deleteOneLike(Long likeId) {

        likeRepository.deleteById(likeId);
    }
}
