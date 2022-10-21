package com.dehghan.questapp.controllers;

import com.dehghan.questapp.entities.User;
import com.dehghan.questapp.repos.UserRepository;
import com.dehghan.questapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
/**spring boot getirdiği bean bizim repomuza atayacaklar*/
    public  UserController(UserService userService){
        this.userService= userService;
    }


    //Read
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();

    }

    //write
    @PostMapping
    public User createUser(@RequestBody User newUser){ /**gelen istekteki(bodydeki ) bilgileri User Objesine map et
     ve bana user objesini dön*/
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //custom Exception --> Bu User veri tabanında olmayabilir
        //Burada diyorum ki eğer User bulamazsan Null dön bana
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){

      /**Ben sana bir userId ve bir yeni user verecem bunu update et*/
      return userService.updateOneUser(userId, newUser);

    }
    @DeleteMapping("/{userId}")
    //Sadece Id'sını aldığı kişiyi silecek
    public void deleteOneUser(@PathVariable Long userId){

        userService.deleteById(userId);
    }


}
