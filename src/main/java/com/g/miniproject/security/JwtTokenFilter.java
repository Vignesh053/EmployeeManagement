package com.g.miniproject.security;

import com.g.miniproject.entity.User;
import com.g.miniproject.exception.ResourceNotFoundException;
import com.g.miniproject.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwttoken = header.split(" ")[1].trim();

        if(!jwtTokenUtil.validate(jwttoken)){
            filterChain.doFilter(request, response);
            return;
        }
        String username = jwtTokenUtil.getUsername(jwttoken);

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User", "Username", username));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken(username, user.getPassword(), authorities);

        upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(upToken);
        filterChain.doFilter(request, response);
    }
}
