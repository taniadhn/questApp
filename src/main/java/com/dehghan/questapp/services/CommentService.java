package com.dehghan.questapp.services;

import com.dehghan.questapp.entities.Comment;
import com.dehghan.questapp.entities.Post;
import com.dehghan.questapp.entities.User;
import com.dehghan.questapp.repos.CommentRepository;
import com.dehghan.questapp.requests.CommentCreateRequest;
import com.dehghan.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    //Creat Commennt yapmak için bu iki servis ihtiyacımız var
    private PostService postService;
    private UserService userService;

    public CommentService(CommentRepository commentRepository, PostService postService, UserService userService){

        this.commentRepository= commentRepository;
        this.postService= postService;
        this.userService=userService;
    }


    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {

        /** userId & postId ayrı ayrı de gelebilir, ikisinin birlikte gelme olasılığı var*/
        if(userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());

        }else if(postId.isPresent()){
            //Get deyince içerisindeki value alıyoruz aslında
            return commentRepository.findByPostId(postId.get());
        }else
            return commentRepository.findAll();

    }



    public Comment createOneComment(CommentCreateRequest createRequest) {

        Post post= postService.getOnePostById(createRequest.getPostId());
        User user = userService.getOneUserById(createRequest.getUserId());
        //Bunların ikisininde Null olmaması lazım
        if(post !=null && user !=null){
            //Comment Objesi yaratacağız
            Comment toSave = new Comment();
            toSave.setId(createRequest.getId());
            toSave.setText(createRequest.getText());
            //DataBase getirdiğimiz post set edeceğiz
            toSave.setPost(post);
            toSave.setUser(user);
            return  commentRepository.save(toSave);
        }else
            return null;

    }

    public Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {

        //ID'sı verilen Commentın var olup olmadığı kontrol edilmesi gerekecek
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){

            Comment toUpdateComment = comment.get();
            toUpdateComment.setText(commentUpdateRequest.getText());
            commentRepository.save(toUpdateComment);
            return toUpdateComment;
        }else{
            return  null;
        }
    }

    public void deleteOneComment(Long commentId) {


        commentRepository.deleteById(commentId);
    }

    public Comment getOneCommentById(Long commentId) {

        return commentRepository.findByCommentId(commentId);
    }
}
