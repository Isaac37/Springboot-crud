package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private  StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public  Student getStudentById(Long id) {
        return  studentRepository.findById(id).orElse(null);
    }

    public void  deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public  boolean existsByNameAndSurname (String name, String surname) {
        return studentRepository.existsByNameAndSurname(name, surname);
    }

}
