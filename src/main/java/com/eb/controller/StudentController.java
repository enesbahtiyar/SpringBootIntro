package com.eb.controller;

import com.eb.domain.Student;
import com.eb.dto.StudentDTO;
import com.eb.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentWithPathVariable(@PathVariable("id")Long id)
    {
        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    //Delete Student

    @DeleteMapping("/{id}")  //http://localhost:8080/students/1
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable("id")Long id)
    {
        studentService.deleteStudent(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "student has been deleted successfully");
        map.put("success", "true");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //update a student using their id
    @PutMapping("/{id}") //http://localhost:8080/students/1
    public ResponseEntity<Map<String, String>> updateStudent(@Valid @PathVariable("id")Long id, @RequestBody StudentDTO student)
    {
        studentService.updateStudent(id, student);

        Map<String, String> map = new HashMap<>();
        map.put("message", "student has been successfully updated");
        map.put("success", "true");

        return ResponseEntity.ok(map);
    }

    @GetMapping("/page") //http://localhost:8080/students/page?page = 1
    public ResponseEntity<Page<Student>> getAllStudentWithPage(@RequestParam("page") int page,
                                                               @RequestParam("size") int size,
                                                               @RequestParam("sort") String prop,
                                                               @RequestParam("direction") Sort.Direction direction)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<Student> pageOfStudents = studentService.getAllStudentsWithPage(pageable);

        return ResponseEntity.ok(pageOfStudents);
    }
}
