package com.endava.parkinglot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret.custom_jwt_secret}")
    private String SECRET;

    public String generateAccessToken(String username){
        Date expirationDate = Date.from(ZonedDateTime.now().plusHours(24).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("PARKING_LOT")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withIssuer("PARKING_LOT")
                .withSubject("User details")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getClaim("username").asString();
    }

}
