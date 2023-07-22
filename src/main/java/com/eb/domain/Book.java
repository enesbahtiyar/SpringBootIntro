package com.eb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.beans.BeanProperty;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("bookName") //name for JSON
    //@Column(name = "bookName")
    private String name;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
