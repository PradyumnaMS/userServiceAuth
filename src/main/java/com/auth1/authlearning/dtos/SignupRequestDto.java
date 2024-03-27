package com.auth1.authlearning.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignupRequestDto {
    private String email;
    private String password;
    private String name;

}
