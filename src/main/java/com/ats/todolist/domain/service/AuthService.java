package com.ats.todolist.domain.service;

import com.ats.todolist.domain.dto.AuthResponseDto;
import com.ats.todolist.domain.dto.LoginDto;
import com.ats.todolist.domain.dto.UserRegistrationDto;

public interface AuthService {

    String userRegistration(UserRegistrationDto userRegistrationDto);

    AuthResponseDto login(LoginDto loginDto);
}
