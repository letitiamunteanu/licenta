package com.example.CreateAccount.Security.Jwt.Controller;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import static com.example.CreateAccount.Security.Jwt.Functionality.JwtCreator.accessTokenCreator;
import static com.example.CreateAccount.Security.Jwt.Functionality.JwtCreator.refreshTokenCreator;
import static com.example.CreateAccount.Security.Jwt.Functionality.JwtDecoder.jwtDecoder;

@RestController
@RequestMapping("/api/jwt")
@CrossOrigin("*")
public class JwtController {

    @GetMapping("/createAccessToken")
    public String createJWT(String username, String issuer, String role){
        return accessTokenCreator(username, issuer, role);
    }

    @GetMapping("/createRefreshToken")
    public String createRefreshJWT(String username, String issuer){
        return refreshTokenCreator(username,issuer);
    }

    @GetMapping("/decodeJwt")
    public String decodeJWT(@RequestHeader("Authorization") String token) throws ParseException {
        return jwtDecoder(token);
    }
}
