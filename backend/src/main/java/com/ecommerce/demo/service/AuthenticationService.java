package com.ecommerce.demo.service;

import com.ecommerce.demo.exception.AuthenticationFailedException;
import com.ecommerce.demo.model.AuthenticationToken;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
    public User getUser(String token){
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken))
            return null;
        return authenticationToken.getUser();

    }
    public void authenticate(String token){
        if(Objects.isNull(token))
            throw new AuthenticationFailedException("Token not present");
        if(Objects.isNull(this.getUser(token)))
            throw new AuthenticationFailedException("Token is not valid");

    }
}
