package com.dehghan.questapp.requests;


import lombok.Data;

@Data
public class PostCreatRequest {

    Long id;  /**Sadece Postman hızlı denemek amacı için yazıldı*/
    String text;
    String title;
    Long userId;
}
