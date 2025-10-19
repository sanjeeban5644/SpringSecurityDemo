package com.sanjeeban.SpringSecurity.Services;

import com.sanjeeban.SpringSecurity.DTO.LoginDto;
import com.sanjeeban.SpringSecurity.DTO.SignupDto;
import com.sanjeeban.SpringSecurity.DTO.UserDto;
import com.sanjeeban.SpringSecurity.Entity.UserEntity;
import com.sanjeeban.SpringSecurity.Repositories.UserRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Component
@Service
public class AuthService {


    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public AuthService(UserRepository userRepository,ModelMapper modelMapper,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JwtService jwtService){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(newUser);



        return modelMapper.map(newUser,UserDto.class);
    }

    public List<UserEntity> getAllUsers() {
            List<UserEntity> listOfUsers = userRepository.getAllUsers();
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("User is : "+user.getUsername());
            return listOfUsers;
    }

    public String login(LoginDto request) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return jwtService.generateToken(user);

    }
}
