package com.dhruvlab.cloudshare.service;

import com.dhruvlab.cloudshare.dto.LoginRequestDto;
import com.dhruvlab.cloudshare.dto.RegisterRequestDto;
import com.dhruvlab.cloudshare.entity.UserEntity;
import com.dhruvlab.cloudshare.repository.UserRepository;
import com.dhruvlab.cloudshare.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String registerUser(RegisterRequestDto registerRequestDto) {

        if(userRepository.findByEmail(registerRequestDto.getEmail()).isPresent())
            return "Email already exists";

        UserEntity userEntity = new UserEntity();

        userEntity.setName(registerRequestDto.getName()) ;
        userEntity.setEmail(registerRequestDto.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerRequestDto.getPassword()));
        userEntity.setRole(registerRequestDto.getRole());

        userRepository.save(userEntity);

        return "User Register Successfully";
    }

    @Override
    public String loginUser(LoginRequestDto dto) {

        UserEntity user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(()-> new RuntimeException(("user not found")));

        if(bCryptPasswordEncoder.matches(dto.getPassword(),user.getPassword()))
            return jwtUtil.generateToken(user.getEmail());

        throw new RuntimeException("invalid password");
    }
}
