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

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    UserServiceImpl userService;
    RoleServiceImpl roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String userList(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String createForm(User user, Model model) {
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);
        return "new";
    }

    @PostMapping("/users/new")
    public String create(User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{userId}")
    public String delete(@PathVariable("userId") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{userId}")
    public String updateForm(Model model, @PathVariable("userId") Long id) {
        List<Role> listRoles = roleService.findAll();
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("listRoles", listRoles);
        return "edit";
    }

    @PostMapping("/users/edit")
    public String update(User user, Role role) {
        userService.update(user);
        return "redirect:/admin/users";
    }

}
