package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserService userService;
    @Autowired
    public UserDetailsServiceImp(UserServiceImp userServiceImp) {
        this.userService = userServiceImp;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь " + username + " не найден");
        }
        return user;
    }
}
