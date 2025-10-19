package com.sanjeeban.SpringSecurity.Controllers;


import com.sanjeeban.SpringSecurity.DTO.LoginDto;
import com.sanjeeban.SpringSecurity.DTO.SignupDto;
import com.sanjeeban.SpringSecurity.DTO.UserDto;
import com.sanjeeban.SpringSecurity.Entity.UserEntity;
import com.sanjeeban.SpringSecurity.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService=authService;
    }


    @GetMapping("/")
    public String homePage(){
        return "redirect:/login.html";
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> response = authService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto request){
        UserDto userDto = authService.signUp(request);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/loginPage")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto request){

        String responseString = authService.login(request);
        UserDto response = new UserDto();
        response.setRemarks(responseString);
        return ResponseEntity.ok(response);

    }




}
