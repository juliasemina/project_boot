package com.example.security_boot.service;

import com.example.security_boot.entity.Role;
import com.example.security_boot.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    void save(User user);

    User getUserbyId(Long id);

    void delete(Long id);

    Set<Role> getAllRols();

    User findByUsername(String username);
}
