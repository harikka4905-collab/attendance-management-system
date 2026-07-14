package com.am.attendancemanagement.Controller;

import com.am.attendancemanagement.entity.Student;
import com.am.attendancemanagement.entity.Subject;
import com.am.attendancemanagement.entity.User;
import com.am.attendancemanagement.repository.StudentRepository;
import com.am.attendancemanagement.repository.SubjectRepository;
import com.am.attendancemanagement.repository.UserRepository;

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
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentController(
            StudentRepository studentRepository,
            UserRepository userRepository,
            SubjectRepository subjectRepository,
            PasswordEncoder passwordEncoder) {

        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add/{subjectId}")
    public ResponseEntity<?> addStudent(
            @PathVariable Long subjectId,
            @RequestBody Student student) {

        if (student.getRollNumber() == null ||
                student.getRollNumber().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("Roll number is required");
        }

        if (studentRepository
                .existsByRollNumber(student.getRollNumber())) {

            return ResponseEntity
                    .badRequest()
                    .body("Roll number already exists");
        }

        Subject subject = subjectRepository
                .findById(subjectId)
                .orElse(null);

        if (subject == null) {

            return ResponseEntity
                    .badRequest()
                    .body("Subject not found");
        }

        User user = new User();

        user.setUsername(student.getRollNumber());

        user.setPassword(
                passwordEncoder.encode("student123")
        );

        user.setRole("STUDENT");

        User savedUser = userRepository.save(user);

        student.setUser(savedUser);

        student.getSubjects().add(subject);

        Student savedStudent =
                studentRepository.save(student);

        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/subject/{subjectId}")
    public List<Student> getStudentsBySubject(
            @PathVariable Long subjectId) {

        return studentRepository
                .findBySubjectsId(subjectId);
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