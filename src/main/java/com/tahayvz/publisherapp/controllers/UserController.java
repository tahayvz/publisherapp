package com.tahayvz.publisherapp.controllers;

import com.tahayvz.publisherapp.domain.User;
import com.tahayvz.publisherapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController extends CommonMethods{

    @Autowired
    private UserService userService;

    @GetMapping({"/","/loginpage"})
    public String loginPage(Model model) {
        if (getActiveLoggedUserEmail() == "anonymousUser" || getActiveLoggedUserEmail() == null) {
            System.out.println("user not logged in : " + getActiveLoggedUserEmail());
            return "userlogin";
        } else {
            System.out.println("user logged in : " + getActiveLoggedUserEmail());
            return "redirect:/index";
        }
    }

    @GetMapping("/registerpage")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "userregister";
    }

    @PostMapping("registerpage")
    public String registering(@ModelAttribute User user) {
        userService.save(user);
        return "userlogin";
    }

    @GetMapping("/add-user")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @RequestMapping("add-user-post")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/dashboard-page";
    }
}
