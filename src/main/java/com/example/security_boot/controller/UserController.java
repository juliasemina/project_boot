package com.example.security_boot.controller;

import com.example.security_boot.dao.RoleRepository;
import com.example.security_boot.entity.Role;
import com.example.security_boot.entity.User;
import com.example.security_boot.service.RoleService;
import com.example.security_boot.service.UserService;
import com.example.security_boot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        model.addAttribute("users", userService.getUsers());
        return "users/index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("admin/open_user")
    public String getUser(@RequestParam(value = "id", required = false) Long id, ModelMap model) {
        System.out.println(userService.getUserbyId(id));
        model.addAttribute("user", userService.getUserbyId(id));
        return "users/get_user";
    }

    @GetMapping("admin/list_users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/list_users";
    }

    @GetMapping("admin/add_user")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "users/add_user";
    }

    @PostMapping("admin/add_user")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "allRoles", required = false) String[] allRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : allRoles) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.save(user);
        return "redirect:/admin/list_users";
    }

    @GetMapping("admin/edit_user")
    public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserbyId(id));

        return "users/edit_user";
    }

    @PatchMapping("admin/edit_user")
    public String edit(@ModelAttribute("user") User user, @RequestParam(value = "allRoles", required = false) String[] allRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : allRoles) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.save(user);
        return "redirect:/admin/list_users";
    }

    @GetMapping("admin/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        userService.delete(id);
        return "redirect:/admin/list_users";
    }

    @GetMapping("/hello_user")
    public String helloPage(Model model, @AuthenticationPrincipal UserDetails curUser) {
        User user = userService.findByUsername(curUser.getUsername());
        model.addAttribute("curUser", user);
        model.addAttribute("users", userService.getUsers());
        return "users/hello_user";
    }

    @RequestMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/login";
    }
}
