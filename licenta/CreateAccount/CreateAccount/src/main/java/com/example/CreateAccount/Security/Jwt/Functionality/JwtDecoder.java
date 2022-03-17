package com.example.CreateAccount.Security.Jwt.Functionality;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtDecoder {

    private final static String SECRET_KEY = "secretkey";
    private final static Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public static String jwtDecoder(String token) {

        System.out.println("token: " + token);
        try {

            String JwtToken = token.substring("Bearer ".length());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(JwtToken);

            String role = decodedJWT.getClaim("role").toString().replace("\"", "");
            return decodedJWT.getSubject() + " " + role;
        }
        catch(Exception e) {
            if(e.getMessage().contains("expired")) {
                System.out.println("return din decoder");
                return "Expired";
            }else {
                System.out.println("invaid din decoder");
                return "Invalid";
            }
        }

    }
}
