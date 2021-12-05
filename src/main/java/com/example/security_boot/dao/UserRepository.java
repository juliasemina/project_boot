package com.example.security_boot.dao;

import com.example.security_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.name=?1")
//    User findByUserName(String username);

    User findUserByName(String username);
}