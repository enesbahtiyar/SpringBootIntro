package com.eb.service;

import com.eb.domain.Student;
import com.eb.dto.StudentDTO;
import com.eb.exceptions.ConflictException;
import com.eb.exceptions.ResourceNotFoundException;
import com.eb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void deleteStudent(Long id)
    {
        repository.deleteById(id);
    }

    public void updateStudent(Long id, StudentDTO studentDTO)
    {
        Student student = getStudentById(id);

        boolean emaiilExists = repository.existsByEmail(studentDTO.getEmail());

        boolean emailBelongsToTheSameStudent = studentDTO.getEmail().equals(student.getEmail());

        if(emaiilExists && !emailBelongsToTheSameStudent)
        {
            throw new ConflictException("student with that email already exists: " + studentDTO.getEmail());
        }

        student.setName(studentDTO.getName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());

        repository.save(student);
    }

    public Page<Student> getAllStudentsWithPage(Pageable pageable)
    {
        return repository.findAll(pageable);
    }

    public List<Student> getStudentByLastName(String lastName)
    {
        return repository.findAllByLastName(lastName);
    }

    public List<Student> getAllStudentByGradeWithJPQL(Integer grade)
    {
        return repository.findStudentsByGradeWithJPQL(grade);
    }

    public StudentDTO getStudentDTOById(Long id) {

        return repository.findStudentDTOById(id).orElseThrow(()-> new ResourceNotFoundException("Couldn't find the student with specified ID: "+id));

    }
}
