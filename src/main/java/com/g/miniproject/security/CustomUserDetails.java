package com.g.miniproject.security;

import com.g.miniproject.entity.User;
import com.g.miniproject.exception.ResourceNotFoundException;
import com.g.miniproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->{
            throw new ResourceNotFoundException("User", "UserName", username);
        });

        Set<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
            new SimpleGrantedAuthority(role.getRoleName())
        ).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
}
