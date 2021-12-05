package com.example.security_boot.service;

import com.example.security_boot.entity.Role;
import com.example.security_boot.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    public List<User> getUsers();
    public User findByUsername(String username);
    public User save(User user);
    public User getUserbyId(Long id);
    public void delete(Long id);

    }
