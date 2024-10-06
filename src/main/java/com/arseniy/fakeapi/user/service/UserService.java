package com.arseniy.fakeapi.user.service;


import com.arseniy.fakeapi.exceptions.NotFoundException;
import com.arseniy.fakeapi.user.domain.model.User;
import com.arseniy.fakeapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private UserRepository userRepository;


    public User addUser(String username, String name){

        var user = User.builder()
                .name(name)
                .username(username)
                .build();

        return userRepository.save(user);
    }

    public void removeUser (Long id){
        userRepository.deleteById(id);
    }

    public User getUser(Long userId) throws NotFoundException {
       var user = userRepository.findById(userId);
       if(user.isEmpty()){
           throw new NotFoundException();
       }

       return user.get();

    }

    public User editUser(Long userId ,  String username, String name ) throws NotFoundException {
        var user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new NotFoundException();
        }

        return userRepository.updateUser(name, username, userId);


    }


}
