package com.examen.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.examen.dtos.UserDto;
import com.examen.entities.User;
import com.examen.exceptions.AppException;
import com.examen.mappers.UserMapper;
import com.examen.repositories.UserRepository;
import com.examen.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    @Value("{security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createToken(UserDto dto){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 10_800_000);    //equivale a una hora

        return JWT.create()
                .withIssuer(dto.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("name", dto.getName())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .email(decoded.getIssuer())
                .name(decoded.getClaim("name").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

         User user = userRepository.findByEmail(decoded.getIssuer())
                 .orElseThrow(() ->  new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(user), null, Collections.emptyList());
    }
}
