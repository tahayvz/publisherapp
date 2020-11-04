package com.tahayvz.publisherapp.services;


import com.tahayvz.publisherapp.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(User user);

    User findByEmail(String email);

    User findByUsername(String username);

    User findById(Long id);

    User deleteUser(Long id);

    User updateUser(Long id);
}