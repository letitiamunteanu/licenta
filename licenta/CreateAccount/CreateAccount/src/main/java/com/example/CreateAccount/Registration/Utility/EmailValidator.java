package com.example.CreateAccount.Registration.Utility;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    private final String regex = "^(.+)@(.+)$";
    private final Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean test(String email) {
        Matcher matcher = pattern.matcher(email);
        System.out.println(email +" : "+ matcher.matches());
        return matcher.matches();
    }
}
