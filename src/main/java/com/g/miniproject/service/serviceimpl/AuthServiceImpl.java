package com.g.miniproject.service.serviceimpl;

import com.g.miniproject.dto.JwtAuthResponse;
import com.g.miniproject.dto.LoginDto;
import com.g.miniproject.dto.RegisterDto;
import com.g.miniproject.entity.Role;
import com.g.miniproject.entity.User;
import com.g.miniproject.exception.EmployeeApiException;
import com.g.miniproject.repository.RoleRepository;
import com.g.miniproject.repository.UserRepository;
import com.g.miniproject.security.JwtTokenUtil;
import com.g.miniproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    @Override
    public String registerUser(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new EmployeeApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new EmployeeApiException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        User newUser = new User();

        newUser.setEmail(registerDto.getEmail());
        newUser.setUsername(registerDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName("ROLE_USER");

        roles.add(userRole);

        newUser.setRoles(roles);

        userRepository.save(newUser);

        return "User Successfully registered";
    }





    @Override
    public JwtAuthResponse loginUser(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken uptoken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(uptoken));

        String jwtToken = jwtTokenUtil.generateToken(loginDto.getUsername());

        Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());

        String role = null;

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            List<Role> dbRoles = user.getRoles().stream()
                    .filter(_role ->_role.getRoleName().equalsIgnoreCase("ROLE_ADMIN"))
                    .collect(Collectors.toList());
            Role userRole;
            if(dbRoles.size() > 0){
                userRole = dbRoles.get(0);
            }else {
                userRole = user.getRoles().stream().findFirst().get();
            }

            role = userRole.getRoleName();

        }

        JwtAuthResponse response = new JwtAuthResponse();

        response.setAccessToken(jwtToken);
        response.setRole(role);

        return response;
    }
}
