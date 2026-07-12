package com.sam.attendance.controller;

import com.sam.attendance.entity.Subject;
import com.sam.attendance.repository.SubjectRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(
            SubjectRepository subjectRepository) {

        this.subjectRepository = subjectRepository;
    }

    // GET ALL SUBJECTS

    @GetMapping("/all")
    public List<Subject> getAllSubjects() {

        return subjectRepository.findAll();
    }

    // GET SUBJECT COUNT

    @GetMapping("/count")
    public long getSubjectCount() {

        return subjectRepository.count();
    }

    // ADD SUBJECT

    @PostMapping("/add")
    public ResponseEntity<?> addSubject(
            @RequestBody Subject subject) {

        if (subject.getSubjectName() == null ||
                subject.getSubjectName().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("Subject name is required");
        }

        Subject savedSubject =
                subjectRepository.save(subject);

        return ResponseEntity.ok(savedSubject);
    }

    // GET SUBJECT BY ID

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(
            @PathVariable Long id) {

        return subjectRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity
                                .notFound()
                                .build()
                );
    }

    // DELETE SUBJECT

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSubject(
            @PathVariable Long id) {

        if (!subjectRepository.existsById(id)) {

            return ResponseEntity
                    .badRequest()
                    .body("Subject not found");
        }

        subjectRepository.deleteById(id);

        return ResponseEntity.ok(
                "Subject deleted successfully"
        );
    }
}