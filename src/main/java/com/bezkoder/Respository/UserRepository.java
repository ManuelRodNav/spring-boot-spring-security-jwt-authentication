package com.bezkoder.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;

import lombok.extern.java.Log;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByEmail(String email);
}
