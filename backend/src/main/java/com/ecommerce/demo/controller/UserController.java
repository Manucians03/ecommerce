package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.user.SignInDto;
import com.ecommerce.demo.dto.user.SignInResponseDto;
import com.ecommerce.demo.dto.user.SignUpDto;
import com.ecommerce.demo.dto.user.SignUpResponseDto;
import com.ecommerce.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpDto signupDto){
        return new ResponseEntity<>(userService.signup(signupDto), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInDto signInDto){
        return new ResponseEntity<>(userService.signIn(signInDto), HttpStatus.OK);
    }
}

