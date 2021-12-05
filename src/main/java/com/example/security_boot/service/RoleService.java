package com.example.security_boot.service;

import com.example.security_boot.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public List<Role> getAllRoles();
    public Role getRoleByName(String rolename);

}
