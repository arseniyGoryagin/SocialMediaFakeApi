package com.arseniy.fakeapi.user.controller;


import com.arseniy.fakeapi.exceptions.NotFoundException;
import com.arseniy.fakeapi.user.domain.dto.UserAddRequest;
import com.arseniy.fakeapi.user.domain.dto.UserEditRequest;
import com.arseniy.fakeapi.user.domain.model.User;
import com.arseniy.fakeapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody UserAddRequest request){


        var user = userService.addUser(request.getUsername(), request.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws NotFoundException {

        var user = userService.getUser(id);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){

        userService.removeUser(id);

        return new ResponseEntity<>("User removed", HttpStatus.OK);

    }


    @PutMapping("{id}")
    public ResponseEntity<User> editUser(@RequestBody UserEditRequest request) throws NotFoundException {

        var user = userService.editUser(request.getId(), request.getUsername(), request.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(NotFoundException e){
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUserNotFoundException(Exception e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
