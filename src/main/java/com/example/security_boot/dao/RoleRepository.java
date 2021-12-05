package com.example.security_boot.dao;

import com.example.security_boot.entity.Role;
import com.example.security_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//    @Query("SELECT u FROM Role u WHERE u.name=?1")
//    Role getRoleByName(String roleName);

    Role getRoleByName(String roleName);

}