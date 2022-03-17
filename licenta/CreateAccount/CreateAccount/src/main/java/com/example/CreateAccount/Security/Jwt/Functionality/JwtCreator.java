package com.example.CreateAccount.Security.Jwt.Functionality;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class JwtCreator {

    //cheie criptata si apoi decriptata!!!
    private final static Algorithm algorithm = Algorithm.HMAC256("secretkey".getBytes());

    public static String accessTokenCreator(String username, String issuer, String role) {

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
                .withIssuer(issuer)
                .withClaim("role", role)
                .sign(algorithm);
    }

    public static String refreshTokenCreator(String username, String issuer){

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 240 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
