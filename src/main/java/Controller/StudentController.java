package com.sam.attendance.controller;

import com.sam.attendance.entity.Student;
import com.sam.attendance.entity.User;
import com.sam.attendance.repository.StudentRepository;
import com.sam.attendance.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentController(
            StudentRepository studentRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(
            @RequestBody Student student) {

        if (studentRepository.existsByRollNumber(
                student.getRollNumber())) {

            return ResponseEntity
                    .badRequest()
                    .body("Roll number already exists");
        }

        if (userRepository
                .findByUsername(student.getRollNumber())
                .isPresent()) {

            return ResponseEntity
                    .badRequest()
                    .body("Login account already exists");
        }

        User user = new User();

        user.setUsername(student.getRollNumber());

        user.setPassword(
                passwordEncoder.encode("student123")
        );

        user.setRole("STUDENT");

        User savedUser = userRepository.save(user);

        student.setUser(savedUser);

        Student savedStudent =
                studentRepository.save(student);

        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    @GetMapping("/count")
    public long getStudentCount() {

        return studentRepository.count();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        if (!studentRepository.existsById(id)) {

            return ResponseEntity
                    .badRequest()
                    .body("Student not found");
        }

        studentRepository.deleteById(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }
}