package com.am.attendancemanagement.repository;

import com.am.attendancemanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {

    // Find logged-in student using roll number
    Optional<Student> findByRollNumber(String rollNumber);

    // Check duplicate roll number
    boolean existsByRollNumber(String rollNumber);

    // Find students enrolled in a particular subject
    List<Student> findBySubjectsId(Long subjectId);
}