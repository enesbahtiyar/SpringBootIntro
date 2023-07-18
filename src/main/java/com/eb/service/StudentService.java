package com.eb.service;

import com.eb.domain.Student;
import com.eb.exceptions.ConflictException;
import com.eb.exceptions.ResourceNotFoundException;
import com.eb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents()
    {
        return repository.findAll();
    }

    public void saveStudent(Student student)
    {
        if(repository.existsByEmail(student.getEmail()))
        {
            throw new ConflictException("student with email: " + student.getEmail() + " already exists");
        }

        repository.save(student);
    }

    public Student getStudentById(Long id)
    {
        Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("couldn't find the student with id: " + id));
        return student;
    }
}
