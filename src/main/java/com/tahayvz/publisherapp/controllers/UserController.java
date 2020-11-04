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

    @GetMapping("/update-user-page")
    private String updateUserPage(Model model) {
        User activeUser = userService.findByEmail(getActiveLoggedUserEmail());
        System.out.println(activeUser.toString());
        model.addAttribute("userObject", activeUser);
        return "userupdate";
    }

    @PostMapping("/update-user-post/{id}")
    private String updateUserPost(@PathVariable(name = "id") Long id, @ModelAttribute User user) {
        User changedUser = userService.findById(id);
        changedUser.setEmail(user.getEmail());
        changedUser.setName(user.getName());
        userService.updateUser(id);
        return "redirect:/index";
    }

    @RequestMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        userService.deleteUser(id);

        return "redirect:/index";
    }
}
