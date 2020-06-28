package com.solarcity.service;

import com.solarcity.dao.UserRepository;
import com.solarcity.model.DAOUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(final UserRepository userRepository,
                                 final PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final DAOUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>(0));
    }

    public DAOUser save(final com.solarcity.model.User user) {
        final DAOUser newUser = new DAOUser();
        newUser.setUsername(user.username());
        newUser.setPassword(bcryptEncoder.encode(user.password()));
        return userRepository.save(newUser);
    }
}