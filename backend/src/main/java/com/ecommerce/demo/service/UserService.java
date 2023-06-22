package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.user.SignInDto;
import com.ecommerce.demo.dto.user.SignInResponseDto;
import com.ecommerce.demo.dto.user.SignUpDto;
import com.ecommerce.demo.dto.user.SignUpResponseDto;
import com.ecommerce.demo.exception.AuthenticationFailedException;
import com.ecommerce.demo.exception.CustomException;
import com.ecommerce.demo.model.AuthenticationToken;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    @Transactional
    public SignUpResponseDto signup(SignUpDto signupDto) {

        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail())))
            throw new CustomException("User already present");
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(encryptedPassword);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setFirstName(signupDto.getFirstName());
        user.setLastName(signupDto.getLastName());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);
        SignUpResponseDto signupResponseDto = new SignUpResponseDto("success", "user created successfully");
        return signupResponseDto;
    }

    private String hashPassword(String encryptedPassword) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(encryptedPassword.getBytes());
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(Objects.isNull(user))
            throw new AuthenticationFailedException("User is not valid");
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword())))
                throw new AuthenticationFailedException("Password is not correct");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AuthenticationToken authenticationToken=authenticationService.getToken(user);

        if(Objects.isNull(authenticationToken))
            throw new CustomException("Token is present");
        return new SignInResponseDto("success", authenticationToken.getToken());
    }
}

