package com.dhruvlab.cloudshare.service;

import com.dhruvlab.cloudshare.dto.LoginRequestDto;
import com.dhruvlab.cloudshare.dto.RegisterRequestDto;

public interface IUserService {

    String registerUser(RegisterRequestDto registerRequestDto);

    String loginUser(LoginRequestDto dto);
}
