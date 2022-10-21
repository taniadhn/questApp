package com.dehghan.questapp.services;


import com.dehghan.questapp.entities.User;
import com.dehghan.questapp.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {

       return  userRepository.findAll();

    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser); /**Daha sonra o User Objesini DataBase save ediyim*/
    }

    public User getOneUserById(Long userId) {

        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {

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

    public void deleteById(Long userId) {

        userRepository.deleteById(userId);
    }
}
