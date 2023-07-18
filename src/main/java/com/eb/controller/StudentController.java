package com.eb.controller;

import com.eb.domain.Student;
import com.eb.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students") //http://localhost:8080/students
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @GetMapping("") //http://localhost:8080/students + GET
    /*
        Get
        Post
        Put
        Delete

        you can use the default format just for 4 times
     */
    private ResponseEntity<List<Student>> getAll()
    {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);        //returns students && http status: 200
    }

    //save a student
    @PostMapping //http://localhost:8080/students + Post
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student)
    {
        studentService.saveStudent(student);

        Map<String, String> map = new HashMap<>();
        map.put("message", "student has been save successfully");
        map.put("success", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);  //HTTPStatus: 201 (created)
    }
    /*
        {
            "name": "jace",
            "grade": 90,
        }
     */

    //get a student with their id
    @GetMapping("/getstudent")            //http://localhost:8080/students/query?id=1
    public ResponseEntity<Student> getById(@RequestParam Long id)
    {
        Student student = studentService.getStudentById(id);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
