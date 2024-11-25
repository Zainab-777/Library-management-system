
    package com.java.springBoot.app.Service;

    import java.util.ArrayList;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

import com.java.springBoot.app.Request.AuthRequest;
    import com.java.springBoot.app.Model.User;
    import com.java.springBoot.app.Repository.UserRepository;

    @Service
    public class UserService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), new ArrayList<>());
        }
        

        public User saveUser(AuthRequest authRequest) {
            User user = new User();
            user.setUsername(authRequest.getUsername());
            user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            return userRepository.save(user);
        }

        public boolean isUsernameTaken(String username) {
            return userRepository.existsByUsername(username);
        }
    }
