package com.example.demo.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    private String email;
    private String password;
}
