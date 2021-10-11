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
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommonController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public CommonController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

//    @GetMapping("/user")
//    public String getUserHomePage(Principal principal, Model model) {
//        if (principal != null) {
//            String s = principal.getName();
//            model.addAttribute("userinfo", s);
//        }
//        return "user";
//    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal, User user) {
        List<User> users = userService.getAll();
        for (User u : users) {
            u.setRolesIds(u.getRoles().stream().map(r -> r.getId().toString()).collect(Collectors.toList()));
        }
        model.addAttribute("users", users);
        if (principal != null) {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String s = principal.getName();
            model.addAttribute("userinfo", s);
            model.addAttribute("userRoles", authorities);
            List<Role> listRoles = roleService.getAll();
            model.addAttribute("listRoles", listRoles);
        }
        return "user";
    }

//    @GetMapping("/admin")
//    public String getAdminHomePage() {
//        return "admin";
//    }
}

