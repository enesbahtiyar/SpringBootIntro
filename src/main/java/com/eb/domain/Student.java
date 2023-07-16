package com.eb.domain;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor //default constructor
@AllArgsConstructor //parameterized constructor
//@RequiredArgsConstructor //that wont let you set fields without final keywords
public class Student //pojo, entity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) //setter will not be used in this id
    private Long id;

    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be white space")
    @Size(min = 3, max = 30, message = "First name '${validatedValue}' cannot exceed those character limits {min} and {max}")
    @Column(nullable = false, length = 25)
    private String name;

    @NotNull(message = "last name cannot be null")
    @NotBlank(message = "last name cannot be white space")
    @Size(min = 3, max = 30, message = "Last name '${validatedValue}' cannot exceed those character limits {min} and {max}")
    @Column(nullable = false, length = 25)
    private String lastName;

    private Integer grade;

    @Email(message = "provide a valid e-mail") //@ domain ext (.com, .net, etc.)
    @Column(nullable = false, length = 55, unique = true)
    private String email;

    private String phoneNumber;


    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDate = LocalDateTime.now();
}
