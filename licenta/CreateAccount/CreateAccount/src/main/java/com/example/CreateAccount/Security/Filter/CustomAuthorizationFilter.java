package com.example.CreateAccount.Security.Filter;

import com.example.CreateAccount.Security.Jwt.ResponsePojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.example.CreateAccount.Security.Jwt.Functionality.JwtDecoder.jwtDecoder;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/userController/refreshToken")){
            filterChain.doFilter(request,response);

        }
        else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                    String decodedJWT = jwtDecoder(authorizationHeader);

                    if(decodedJWT.equals("Expired") || decodedJWT.equals("Invalid")){
                        response.setStatus(FORBIDDEN.value());
                        response.setContentType(APPLICATION_JSON_VALUE);
                        ResponsePojo res = new ResponsePojo(FORBIDDEN.value(),decodedJWT);
                        new ObjectMapper().writeValue(response.getOutputStream(), res);
                        return;
                    }

                    String username = decodedJWT.split(" ")[0];
                    String role = decodedJWT.split(" ")[1];
                    SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(role);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(userRole));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);

            }
            else{
                filterChain.doFilter(request,response);
            }
        }
    }
}
