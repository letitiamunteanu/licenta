package com.example.pacientiDr.JWT;

import net.minidev.json.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class JwtValidation {

    public static String jwtValidationRequest(String jwt){

        JSONParser parser = new JSONParser();
        RestTemplate restTemplate = new RestTemplate();

        try{

            String fooResourceUrl  = "http://localhost:8889/api/jwt/decodeJwt";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setBearerAuth(jwt.substring(7));
            HttpEntity<String> request = new HttpEntity<>("", headers);

            ResponseEntity<String> response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, request, String.class);

            return response.getBody();
        }
        catch (Exception ex){
            return ex.getMessage();
        }
    }
}
