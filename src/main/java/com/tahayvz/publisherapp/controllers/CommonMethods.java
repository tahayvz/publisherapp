package com.tahayvz.publisherapp.controllers;


import com.tahayvz.publisherapp.domain.User;
import com.tahayvz.publisherapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public abstract class CommonMethods {

    @Autowired
    UserService userService;

    public String getActiveLoggedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = userService.findByUsername(((UserDetails) principal).getUsername());
            return user.getEmail();
        } else {
            return null;
        }
    }

    public String getActiveLoggedUserRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getAuthorities().toString();
        } else {
            return null;
        }
    }
}