package com.example.CreateAccount.UserFunctionality.Controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.CreateAccount.UserFunctionality.Model.Users;
import com.example.CreateAccount.UserFunctionality.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/userController")
@CrossOrigin("*")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService appUserService) {
        this.userService = appUserService;
    }

    @GetMapping("/getUserByUsername/{username}")
    @PreAuthorize("hasAnyAuthority('admin', 'ADMIN', 'doctor', 'DOCTOR')")
    public Users getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserByEmail/{email}")
    @PreAuthorize("hasAnyAuthority('admin', 'ADMIN', 'doctor', 'DOCTOR')")
    public Users getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAnyAuthority('admin', 'ADMIN')")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUsersByFirstName/{firstName}")
    @PreAuthorize("hasAnyAuthority('admin', 'ADMIN', 'doctor', 'DOCTOR')")
    public List<Users> getUsersByFirstName(@PathVariable String firstName) {
        return userService.getUserByFirstName(firstName);
    }

    @GetMapping("/getUsersByLastName/{lastName}")
    @PreAuthorize("hasAnyAuthority('admin', 'ADMIN', 'doctor', 'DOCTOR')")
    public List<Users> getUsersByLastName(@PathVariable String lastName) {
        return userService.getUserByLastName(lastName);
    }

    @PatchMapping("/updateUserAccount/{username}")
    @PreAuthorize("hasAnyAuthority('admin', 'ADMIN', 'doctor', 'DOCTOR', 'pacient', 'PACIENT')")
    public Users updateUserAccount(@PathVariable String username, @RequestBody Map<Object, Object> fields) {

        Users user = userService.getUserByUsername(username);

        fields.forEach((k, v) -> {

            Field field = ReflectionUtils.findField(Users.class, (String) k);
            assert field != null;
            field.setAccessible(true);

            ReflectionUtils.setField(field, user, v);
        });

        return userService.updateUserAccount(username, user);
    }

    @DeleteMapping("deleteUser/{username}")
    @PreAuthorize("hasAnyAuthority('admin', 'ADMIN', 'doctor', 'DOCTOR', 'pacient', 'PACIENT')")
    public String deleteUserAccount(@PathVariable String username) {
        return userService.deleteUserAccount(username);
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secretkey".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                Users user = userService.getUserByUsername(username);

                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", user.getRole())
                        .sign(algorithm);

                System.out.println("user controller " + verifier.verify(accessToken).getClaim("role"));

                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

//    @Lazy
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/signing")
//    public void createAuthenticationToken(@RequestBody LoginRequest loginReq, HttpServletRequest request,
//                                          HttpServletResponse response) throws AuthenticationException, IOException {
//
//
//        // Perform the security
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginReq.getUsername(),
//                        loginReq.getPassword()
//                )
//        );
//
//        // Inject into security context
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // token creation
//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
//
//        Algorithm algorithm = Algorithm.HMAC256("secretkey".getBytes());
//
//        String accessToken = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
//                .withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0))
//                .sign(algorithm);
//
//
//        String refreshToken = JWT.create()
//                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 240 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);
//
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("accessToken", accessToken);
//        tokens.put("refreshToken", refreshToken);
//
//        response.setContentType(APPLICATION_JSON_VALUE);
//        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//        // Return the token
//
//        //return ResponseEntity.ok().body(response);
//    }
//}
