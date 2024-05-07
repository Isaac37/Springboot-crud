package com.example.crud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByNameAndSurname(String name, String surname);
}
