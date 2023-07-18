package com.eb.repository;

import com.eb.domain.Student;
import com.eb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //this is optional
public interface StudentRepository extends JpaRepository<Student, Long> // extending is needed
{
    boolean existsByEmail(String email);
}
