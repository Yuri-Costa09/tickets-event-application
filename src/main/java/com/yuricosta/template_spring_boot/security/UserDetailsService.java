package com.yuricosta.template_spring_boot.security;

import com.yuricosta.template_spring_boot.user.User;
import com.yuricosta.template_spring_boot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()
                -> new UsernameNotFoundException("Usuário não encontrado."));
        return new UserDetailsImpl(user);
    }
}