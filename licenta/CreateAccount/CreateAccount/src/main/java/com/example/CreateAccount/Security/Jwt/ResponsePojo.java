package com.example.CreateAccount.Security.Jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponsePojo implements Serializable {

    private Integer status;
    private String responseBody;
}
