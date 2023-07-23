package com.eb.security.service;

import com.eb.domain.Role;
import com.eb.domain.User;
import com.eb.exceptions.ResourceNotFoundException;
import com.eb.repository.UserRepository;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserServiceImplementation implements UserDetailsService
{
    /*
        in this class we are going to convert:
            1: User to userDetail
            2: role to GrantedAuthority
     */
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        //finding username from our database
        User foundUser = userRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("user couldn't found with name: " + username));

        if(foundUser != null)
        {
            return new org.
                    springframework.
                    security.
                    core.
                    userdetails.
                    User(foundUser.getUserName(),
                    foundUser.getPassword(),
                    buildGrantedAuthority(foundUser.getRoleSet()));
        }else
        {
            throw new ResourceNotFoundException("user not found :" + username);
        }

    }

    private static List<SimpleGrantedAuthority> buildGrantedAuthority(final Set<Role> roles)
    {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role: roles)
        {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        return authorities;
    }
}
