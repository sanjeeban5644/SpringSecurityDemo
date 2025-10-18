package com.sanjeeban.SpringSecurity.Services;

import com.sanjeeban.SpringSecurity.DTO.SignupDto;
import com.sanjeeban.SpringSecurity.DTO.UserDto;
import com.sanjeeban.SpringSecurity.Entity.UserEntity;
import com.sanjeeban.SpringSecurity.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
public class AuthService {


    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AuthService(UserRepository userRepository,ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto signUp(SignupDto request) {

        UserDto response = new UserDto();

        Optional<UserEntity> userEntity = userRepository.findByUsername(request.getUsername());
        if(userEntity.isPresent()){
            response.setRemarks("User Already Exists");
            return response;
//            throw new BadCredentialsException("User already Exists with username : "+request.getUsername());
        }

        UserEntity newUser = modelMapper.map(request,UserEntity.class);

        userRepository.save(newUser);



        return modelMapper.map(newUser,UserDto.class);
    }
}
