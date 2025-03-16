package com.ats.todolist.service.impl;

import com.ats.todolist.security.jwt.JwtTokenProvider;
import com.ats.todolist.dao.RoleDao;
import com.ats.todolist.dao.UserDao;
import com.ats.todolist.dto.AuthResponseDto;
import com.ats.todolist.dto.LoginDto;
import com.ats.todolist.dto.UserRegistrationDto;
import com.ats.todolist.entity.Role;
import com.ats.todolist.entity.User;
import com.ats.todolist.exception.ResourceNotFoundException;
import com.ats.todolist.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserDao userDao,
                           RoleDao roleDao,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String userRegistration(UserRegistrationDto userRegistrationDto) {
        log.info("Registering new user: {}", userRegistrationDto.getUserName());

        validateUser(userRegistrationDto);

        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setUserName(userRegistrationDto.getUserName());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        // Assign default role
        Role userRole = roleDao.findByRoleName("USER");
        if (userRole == null) {
            log.error("Default role 'ROLE_USER' not found in database.");
            throw new ResourceNotFoundException("User role not found. Please check database setup.");
        }

        user.setRoles(Collections.singleton(userRole));
        userDao.save(user);

        log.info("User '{}' registered successfully.", user.getUserName());
        return "User registered successfully!";
    }

    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        log.info("Attempting login for user: {}", loginDto.getUserNameOrEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        log.info("User '{}' logged in successfully.", loginDto.getUserNameOrEmail());
        return new AuthResponseDto("User logged-in successfully!", token);
    }

    private void validateUser(UserRegistrationDto userRegistrationDto) {
        if (Boolean.TRUE.equals(userDao.existsByUserName(userRegistrationDto.getUserName()))) {
            log.warn("Username '{}' is already taken.", userRegistrationDto.getUserName());
            throw new IllegalArgumentException("Username already exists!");
        }

        if (Boolean.TRUE.equals(userDao.existsByEmail(userRegistrationDto.getEmail()))) {
            log.warn("Email '{}' is already registered.", userRegistrationDto.getEmail());
            throw new IllegalArgumentException("Email already exists!");
        }
    }
}
