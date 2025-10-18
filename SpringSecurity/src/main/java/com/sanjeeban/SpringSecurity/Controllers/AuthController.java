package com.sanjeeban.SpringSecurity.Controllers;


import com.sanjeeban.SpringSecurity.DTO.SignupDto;
import com.sanjeeban.SpringSecurity.DTO.UserDto;
import com.sanjeeban.SpringSecurity.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto request){
        UserDto userDto = authService.signUp(request);
        return ResponseEntity.ok(userDto);
    }




}
