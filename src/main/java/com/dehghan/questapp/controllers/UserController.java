package com.dehghan.questapp.controllers;

import com.dehghan.questapp.entities.User;
import com.dehghan.questapp.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
/**spring boot getirdiği bean bizim repomuza atayacaklar*/
    public  UserController(UserRepository userRepository){
        this.userRepository= userRepository;
    }


    //Read
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();

    }

    //write
    @PostMapping
    public User createUser(@RequestBody User newUser){ /**gelen istekteki(bodydeki ) bilgileri User Objesine map et
     ve bana user objesini dön*/
        return userRepository.save(newUser); /**Daha sonra o User Objesini DataBase save ediyim*/
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //custom Exception --> Bu User veri tabanında olmayabilir
        //Burada diyorum ki eğer User bulamazsan Null dön bana
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){

        /**Update etmek için ilk olarak repository o User bulmamız gerekiyor*/
        Optional<User> user = userRepository.findById(userId);
        //Optional isPresent yani Object var
        if(user.isPresent()){

            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser); //Update edilmiş haLini gidip data base kayıt ediyoruz

            return foundUser;
        }else
            return null;

    }
    @DeleteMapping("/{userId}")
    //Sadece Id'sını aldığı kişiyi silecek
    public void deleteOneUser(@PathVariable Long userId){

        userRepository.deleteById(userId);
    }


}
