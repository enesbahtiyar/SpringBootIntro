package com.eb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
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
    @JsonIgnore()  //ignore that students to prevent the infinite loop
    private Student student;
}
