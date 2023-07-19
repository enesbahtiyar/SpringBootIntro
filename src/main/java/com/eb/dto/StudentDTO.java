package com.eb.dto;

import com.eb.domain.Student;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO
{
    private Long id;

    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be white space")
    @Size(min = 3, max = 30, message = "First name '${validatedValue}' cannot exceed those character limits {min} and {max}")
    private String name;


    private String lastName;

    private Integer grade;

    @Email(message = "provide a valid e-mail") //@ domain ext (.com, .net, etc.)
    private String email;

    private String phoneNumber;


    private LocalDateTime creationDate = LocalDateTime.now();

    public StudentDTO(Student student)
    {
        this.id = student.getId();
        this.name = student.getName();
        this.lastName = student.getLastName();
        this.grade = student.getGrade();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
    }


}
