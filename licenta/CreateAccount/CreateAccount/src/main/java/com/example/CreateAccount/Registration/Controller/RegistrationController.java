package com.example.CreateAccount.Registration.Controller;

import com.example.CreateAccount.Registration.Service.RegisterService;
import com.example.CreateAccount.Registration.Model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegisterService registerService;

    @PostMapping("/save")
    public String register(@RequestBody RegistrationRequest request){
        return registerService.register(request);
    }
}
