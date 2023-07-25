package com.eb.service;

import com.eb.domain.Role;
import com.eb.domain.enums.UserRole;
import com.eb.exceptions.ResourceNotFoundException;
import com.eb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService
{
    @Autowired
    private RoleRepository repository;

    public Role getRoleByType(UserRole roleType)
    {
        Role role = repository.findByName(roleType).orElseThrow(() -> new ResourceNotFoundException("role not found"));

        return role;
    }
}
