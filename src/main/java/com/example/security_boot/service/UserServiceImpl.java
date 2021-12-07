//package com.example.security_boot.service;
//
//import com.example.security_boot.entity.Role;
//import com.example.security_boot.entity.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import java.util.List;
//import java.util.Set;
//
//public interface UserService extends UserDetailsService {
//    List<User> getUsers();
//
//    void save(User user);
//
//    User getUserbyId(Long id);
//
//    void delete(Long id);
//
//    Set<Role> getAllRols();
//
//    User findByUsername(String username);
//}


package com.example.security_boot.service;

import com.example.security_boot.dao.RoleRepository;
import com.example.security_boot.dao.UserRepository;
import com.example.security_boot.entity.Role;
import com.example.security_boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByName(username);
    }

    @Override
    public User save(User user) {

//        User newUser = new User();
//        newUser.setName(user.getName());
//        newUser.setSurname(user.getSurname());
//        newUser.setAge(user.getAge());
//        newUser.setEmail(user.getEmail());
//        newUser.setEnabled(user.isEnabled());
//        newUser.setPass(bCryptPasswordEncoder.encode(user.getPassword()));

//        if (user.getRoles().isEmpty()) {
//            user.addRole(roleRepository.getRoleByName("ROLE_USER"));
//        }
//        Set<Role> roles = user.getRoles();
//        for (Role role : roles) {
//            newUser.addRole(roleRepository.getRoleByName(role.getName()));
//        }
//        if (user.getId() == null) {
//            user.setPass(bCryptPasswordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//
//        } else {
//            Long id = user.getId();
//            newUser.setId(id);

        user.setPass(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User getUserbyId(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}