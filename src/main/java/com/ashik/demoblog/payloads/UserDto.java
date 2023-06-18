package com.ashik.demoblog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters")
    private String name;

    @NotEmpty
    @Email(message = "Email Address is not valid!")
    private  String email;

    @NotEmpty
    @Size(min = 4, max = 10, message = "Password must be min of 4 chars and max of 10 chars !!")
    private String password;

    private String about;

}
