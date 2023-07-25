package com.eb.service;

import com.eb.domain.Role;
import com.eb.domain.User;
import com.eb.domain.enums.UserRole;
import com.eb.dto.UserDto;
import com.eb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public void saveUser(UserDto userDto)
    {


        User newUser = new User();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setUserName(userDto.getUserName());


        //we should not store plain password to db we have to encode
        //newUser.setPassword(userDto.getPassword());


        String password = passwordEncoder.encode(userDto.getPassword());
        newUser.setPassword(password);

        Role role = roleService.getRoleByType(UserRole.ROLE_ADMIN);
        //newUser.setRoleSet(role);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        newUser.setRoleSet(roleSet);

        userRepository.save(newUser);
    }
}
