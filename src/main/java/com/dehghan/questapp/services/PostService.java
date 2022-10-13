package com.dehghan.questapp.services;


import com.dehghan.questapp.entities.Post;
import com.dehghan.questapp.entities.User;
import com.dehghan.questapp.repos.PostRepository;
import com.dehghan.questapp.requests.PostCreatRequest;
import com.dehghan.questapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    public  PostService(PostRepository postRepository, UserService userService){

        this.postRepository= postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {

        if(userId.isPresent())

             return postRepository.findByUserId(userId.get());
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {

        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreatRequest newPostCreatRequest) {

        /**Böyle bir User Var mi?*/
       User user = userService.getOneUser(newPostCreatRequest.getUserId());
       if(user == null)
           return null;
       /**newPostCreateRequest Bir Post Objesine çevirmemiz lazım*/
       Post toSave = new Post();
       toSave.setId(newPostCreatRequest.getId());
       toSave.setText(newPostCreatRequest.getText());
       toSave.setTitle(newPostCreatRequest.getTitle());
       toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updatePost(Long postId, PostUpdateRequest postUpdate) {

        //Bana verdiği Id ile bir Post var mı?
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()){
            Post toUpdatePost = post.get();
            toUpdatePost.setText(postUpdate.getText());
            toUpdatePost.setTitle(postUpdate.getTitle());

            postRepository.save(toUpdatePost);
            return  toUpdatePost;
        }else
            return  null;

    }


    public void deleteOnePost(Long postId) {

        postRepository.deleteById(postId);
    }
}
