package com.bezkoder.springjwt.controllers;


import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.User;

import jakarta.websocket.server.PathParam;
import com.bezkoder.Service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/user/")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Page<User> getAll(Pageable pageable) {
      return  userService.getallusers(pageable);
       
    }
    @GetMapping("/{id}")
    public User getAll(@PathVariable("id") Long id) {
      return  userService.getuserbyid(id);
       
    }
    @PutMapping("/{id}")
    public User putUser(@PathVariable("id") Long id,User reqUser) {
      return  userService.putUser(reqUser, id);
       
    }
    @PostMapping()
    public User postuser(User reqUser) {
      return  userService.postuser(reqUser);
       
    }
    @DeleteMapping("/{id}")
    public String deleteuser(@PathVariable("id")Long id){
        return userService.deleteUser(id);
    }
    
}
