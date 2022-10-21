package com.dehghan.questapp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

    Long id;
    String text;
    //Atacağımız Comment gerçekten var olan bir post atmamız gerekiyor ve var olan bir User ile comment atıyor olmamız lazım
    Long userId;
    Long postId;
}
