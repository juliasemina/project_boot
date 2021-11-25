package com.example.security_boot.dao;

import com.example.security_boot.entity.Role;
import com.example.security_boot.entity.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    List<User> getUsers();
    Set<Role> getAllRols();
    User save(User user);
    User getUserbyId(Long id);
    void delete(Long id);
    User findByUsername(String username);
}
