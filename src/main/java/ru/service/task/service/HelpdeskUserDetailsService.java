package ru.service.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.service.task.controller.entity.UserDto;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class HelpdeskUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public HelpdeskUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto login = userService.getUserByUsername(username);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(login.getCredentials()));

        return new User(login.getUsername(), login.getPassword(), authorities);
    }
}
