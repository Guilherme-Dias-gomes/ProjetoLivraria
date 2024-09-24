package br.com.fiap.projetodima.services;

import br.com.fiap.projetodima.model.User;
import br.com.fiap.projetodima.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenServices {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
    }
}
