package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {

    @Autowired
    private  StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if(studentService.existsByNameAndSurname(student.getName(), student.getSurname())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Student savedStudent = studentService.saveStudent(student);
        return  ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/students/{id}")
    public  Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            //Updating the existing student's information
            if (student.getName() != null) {
                existingStudent.setName(student.getName());
            }
               if (student.getSurname() != null) {
                   existingStudent.setSurname(student.getSurname());
            }


            Student updateStudent = studentService.saveStudent(existingStudent);
            return ResponseEntity.ok(updateStudent);
        }else  {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void>  deleteStudentById(@PathVariable Long id) {
        if (studentService.getStudentById(id) !=null) {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
