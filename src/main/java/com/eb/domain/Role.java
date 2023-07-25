package com.eb.domain;

import com.eb.domain.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @Enumerated(EnumType.STRING) //to bring string names we need to use EnumType.STRING
    private UserRole name;

    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }
}
