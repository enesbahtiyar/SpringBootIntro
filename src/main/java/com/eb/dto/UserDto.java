package com.eb.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    @NotBlank(message = "enter first name")
    private String firstName;

    @NotBlank(message = "enter last name")
    private String lastName;

    @NotBlank(message = "enter UserName")
    @Size(message = "please provide an username between 5 and 20 characters long ")
    private String userName;

    @NotBlank(message="enter password")
    private String password;
}
