package home.catechumen.bootMVC.controller;

import home.catechumen.bootMVC.model.Role;
import home.catechumen.bootMVC.model.User;
import home.catechumen.bootMVC.service.RoleServiceImpl;
import home.catechumen.bootMVC.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;


    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String userList( Model model, Principal principal, User user) {
        List<User> users = userService.getAll();
        UserRolesDTO userRolesDTO = new UserRolesDTO();
        model.addAttribute("users", users);
        if (principal != null) {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String s = principal.getName();
            model.addAttribute("userinfo", s);
            model.addAttribute("userRoles", authorities);
            List<Role> listRoles = roleService.getAll();
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("userRolesDTO", userRolesDTO);
        }
        return "admin";
    }

    @PostMapping("/users/new")
    public String create(User user, UserRolesDTO userRolesDTO) {
        frontRemapping(user, userRolesDTO);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/users/delete/{userId}")
    public String delete(@PathVariable("userId") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/users/edit")
    public String update(User user, UserRolesDTO userRolesDTO) {
        frontRemapping(user, userRolesDTO);
        userService.update(user);
        return "redirect:/admin";
    }

    private void frontRemapping(User user, UserRolesDTO userRolesDTO) {
        List<Long> userIds = userRolesDTO.getRolesIds().stream()
                .mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        List<Role> userRolesSet = roleService.getAll().stream()
                .filter(role -> userIds.contains(role.getId())).collect(Collectors.toList());
        user.setRoles(userRolesSet);
    }
}
