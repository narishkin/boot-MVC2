package home.catechumen.bootMVC.controller;

import home.catechumen.bootMVC.model.Role;
import home.catechumen.bootMVC.model.User;
import home.catechumen.bootMVC.service.RoleServiceImpl;
import home.catechumen.bootMVC.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/users")
    public String userList(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String createForm(Model model) {
        List<Role> listRoles = roleService.getAll();
        User user = new User();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);
        UserRolesDTO userRolesDTO = new UserRolesDTO();
        model.addAttribute("userRolesIds",userRolesDTO);
        return "new";
    }

    @PostMapping("/users/new")
    public String create(User user, UserRolesDTO userRolesDTO) {
        frontRemapping(user, userRolesDTO);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{userId}")
    public String delete(@PathVariable("userId") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{userId}")
    public String updateForm(Model model, @PathVariable("userId") long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        UserRolesDTO userRolesDTO = new UserRolesDTO();
        userRolesDTO.setRolesIds(user.getRoles().stream().map(a -> a.getId().toString()).collect(Collectors.toList()));
        model.addAttribute("listRoles", roleService.getAll());
        model.addAttribute("userRolesIds",userRolesDTO);
        return "edit";
    }

    @PostMapping("/users/edit")
    public String update(User user, UserRolesDTO userRolesDTO) {
        frontRemapping(user, userRolesDTO);
        System.out.println(user);
        userService.update(user);
        return "redirect:/admin/users";
    }

    private void frontRemapping(User user, UserRolesDTO userRolesDTO) {
        List<Long> userIds = userRolesDTO.getRolesIds().stream()
                .mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        System.out.println(userRolesDTO.getRolesIds());
        List<Role> userRolesSet = roleService.getAll().stream()
                .filter(role -> userIds.contains(role.getId())).collect(Collectors.toList());
        user.setRoles(userRolesSet);
    }
}
