package com.eb.repository;

import com.eb.domain.Role;
import com.eb.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(UserRole type);
}
