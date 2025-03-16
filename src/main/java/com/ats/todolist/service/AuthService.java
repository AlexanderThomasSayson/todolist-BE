package com.ats.todolist.service;

import com.ats.todolist.dto.AuthResponseDto;
import com.ats.todolist.dto.LoginDto;
import com.ats.todolist.dto.UserRegistrationDto;

public interface AuthService {

    String userRegistration(UserRegistrationDto userRegistrationDto);

    AuthResponseDto login(LoginDto loginDto);
}
