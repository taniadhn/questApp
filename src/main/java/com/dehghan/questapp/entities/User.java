package com.dehghan.questapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity /** Entity demek yani bu Class DataBase maplenecek*/
@Table(name = "user")
@Data /** Class Getter & Setter otomatik olarak set edecek*/
public class User {

    @Id
    Long id;

    String username;
    String password;

}
