package com.sam.attendance.controller;

import com.sam.attendance.entity.Attendance;
import com.sam.attendance.entity.Student;
import com.sam.attendance.repository.AttendanceRepository;
import com.sam.attendance.repository.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/student-report")
@CrossOrigin
public class StudentReportController {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public StudentReportController(
            AttendanceRepository attendanceRepository,
            StudentRepository studentRepository) {

        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "Student Report Controller Working";
    }

    @GetMapping("/my-report")
    public ResponseEntity<?> getMyReport(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity
                    .status(401)
                    .body("Student not authenticated");
        }

        String username = authentication.getName();

        Optional<Student> studentOptional =
                studentRepository.findByRollNumber(username);

        if (studentOptional.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Student not found for roll number: " + username);
        }

        Student student = studentOptional.get();

        List<Attendance> attendanceList =
                attendanceRepository.findByStudentId(student.getId());

        Map<String, List<Attendance>> subjectMap =
                new LinkedHashMap<>();

        for (Attendance attendance : attendanceList) {

            if (attendance.getSubject() == null) {
                continue;
            }

            String subjectName =
                    attendance.getSubject().getSubjectName();

            subjectMap
                    .computeIfAbsent(
                            subjectName,
                            key -> new ArrayList<>()
                    )
                    .add(attendance);
        }

        List<Map<String, Object>> subjects =
                new ArrayList<>();

        int totalPresent = 0;
        int totalClasses = 0;

        for (Map.Entry<String, List<Attendance>> entry
                : subjectMap.entrySet()) {

            List<Attendance> records = entry.getValue();

            long present = records.stream()
                    .filter(attendance ->
                            "PRESENT".equalsIgnoreCase(
                                    attendance.getStatus()
                            )
                    )
                    .count();

            int total = records.size();
            int absent = total - (int) present;

            double percentage =
                    total == 0
                            ? 0
                            : (present * 100.0) / total;

            Map<String, Object> subject =
                    new LinkedHashMap<>();

            subject.put("subjectName", entry.getKey());
            subject.put("totalClasses", total);
            subject.put("present", present);
            subject.put("absent", absent);
            subject.put("percentage", Math.round(percentage));

            subjects.add(subject);

            totalPresent += (int) present;
            totalClasses += total;
        }

        double overallPercentage =
                totalClasses == 0
                        ? 0
                        : (totalPresent * 100.0) / totalClasses;

        Map<String, Object> report =
                new LinkedHashMap<>();

        report.put("studentName", student.getName());
        report.put("rollNumber", student.getRollNumber());
        report.put("subjects", subjects);
        report.put(
                "overallPercentage",
                Math.round(overallPercentage)
        );

        return ResponseEntity.ok(report);
    }
}