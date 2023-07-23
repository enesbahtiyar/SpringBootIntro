package com.eb.repository;

import com.eb.domain.User;
import com.eb.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUserName(String userName) throws ResourceNotFoundException;
}
