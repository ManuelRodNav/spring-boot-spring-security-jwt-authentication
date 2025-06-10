package com.bezkoder.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Book;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.BookRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Page<User> getallusers(Pageable pageable){
    return userRepository.findAll(pageable);
    }

    public User getuserbyid(Long id){
     return   userRepository.findById(id).orElseThrow();
    }

    public User postuser(User reqUser){
     User usernew = new User();
     usernew.setEmail(reqUser.getEmail());
     usernew.setPassword(reqUser.getPassword());
     usernew.setUsername(reqUser.getUsername());
     userRepository.save(usernew);
     return usernew;
    }

     public User putUser(User reqUser,Long id){
     User usertoupdate= userRepository.findById(id).orElseThrow();
     usertoupdate.setEmail(reqUser.getEmail());
     usertoupdate.setPassword(reqUser.getPassword());
     usertoupdate.setUsername(reqUser.getUsername());
     userRepository.save(usertoupdate);
     return usertoupdate;
    }

    public String deleteUser(Long id){
        Map<String,String> map = new HashMap<>();
        User usertodelete= userRepository.findById(id).orElseThrow();
        if(usertodelete.equals(null)){
            map.put("Accion denegada", "no se ha podido completar la accion");
        }
        userRepository.delete(usertodelete);
        return map.put("Accion realizada","se ha realizado la accion correctamente");
    }
}
