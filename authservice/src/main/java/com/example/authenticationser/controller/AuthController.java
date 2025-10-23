package com.example.authenticationser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authenticationser.dto.AuthDto;
import com.example.authenticationser.dto.ReturnDto;
import com.example.authenticationser.dto.SignupDto;
import com.example.authenticationser.service.AuthService;

    
@RequestMapping("/auth")
@RestController
public class AuthController 
{
    AuthService _AuthService;

    @Autowired
    AuthController(AuthService authService)
    {
        this._AuthService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<ReturnDto> SignUp(@RequestBody SignupDto cred) 
    {
        ReturnDto response = new ReturnDto();
        boolean isSuccess = _AuthService.SignUp(cred, response);
        if(isSuccess)
        {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> Authenticate(@RequestBody AuthDto cred) 
    {
        boolean isAuthenticated = _AuthService.Authenticate(cred);
        if (isAuthenticated) {
            return ResponseEntity.ok("Authentication successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }

}