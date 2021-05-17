package com.eemall.demo.service.impl;

import com.eemall.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername: " + username);
        com.eemall.demo.model.User user = userService.findByEmail(username);
        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException(username + " user not found");
        } else {
            System.out.println("user found: " + user.getPassword());
        }

        String password = user.getPassword();

        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
    }
}

