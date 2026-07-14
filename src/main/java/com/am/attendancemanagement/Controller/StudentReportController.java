package com.am.attendancemanagement.Controller;

import com.am.attendancemanagement.entity.Attendance;
import com.am.attendancemanagement.entity.Student;
import com.am.attendancemanagement.repository.AttendanceRepository;
import com.am.attendancemanagement.repository.StudentRepository;

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

        // 1. Check authentication
        if (authentication == null ||
                !authentication.isAuthenticated()) {

            return ResponseEntity
                    .status(401)
                    .body("Student not authenticated");
        }

        // 2. Get logged-in username
        // For students, username = roll number
        String username = authentication.getName();

        // 3. Find student using roll number
        Optional<Student> studentOptional =
                studentRepository.findByRollNumber(username);

        if (studentOptional.isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Student not found for roll number: "
                                    + username
                    );
        }

        Student student = studentOptional.get();

        // 4. Get this student's attendance
        List<Attendance> attendanceList =
                attendanceRepository.findByStudentId(
                        student.getId()
                );

        // 5. Group attendance by subject
        Map<String, List<Attendance>> subjectMap =
                new LinkedHashMap<>();

        for (Attendance attendance : attendanceList) {

            if (attendance.getSubject() == null) {
                continue;
            }

            String subjectName =
                    attendance
                            .getSubject()
                            .getSubjectName();

            subjectMap
                    .computeIfAbsent(
                            subjectName,
                            key -> new ArrayList<>()
                    )
                    .add(attendance);
        }

        // 6. Calculate subject-wise attendance
        List<Map<String, Object>> subjects =
                new ArrayList<>();

        int totalPresent = 0;
        int totalClasses = 0;

        for (Map.Entry<String, List<Attendance>> entry
                : subjectMap.entrySet()) {

            String subjectName = entry.getKey();

            List<Attendance> records =
                    entry.getValue();

            long present = records
                    .stream()
                    .filter(attendance ->
                            "PRESENT".equalsIgnoreCase(
                                    attendance.getStatus()
                            )
                    )
                    .count();

            int total = records.size();

            int absent =
                    total - (int) present;

            double percentage =
                    total == 0
                            ? 0
                            : (present * 100.0) / total;

            Map<String, Object> subject =
                    new LinkedHashMap<>();

            subject.put(
                    "subjectName",
                    subjectName
            );

            subject.put(
                    "totalClasses",
                    total
            );

            subject.put(
                    "present",
                    present
            );

            subject.put(
                    "absent",
                    absent
            );

            subject.put(
                    "percentage",
                    Math.round(percentage)
            );

            subjects.add(subject);

            totalPresent += (int) present;

            totalClasses += total;
        }

        // 7. Calculate overall attendance
        int totalAbsent =
                totalClasses - totalPresent;

        double overallPercentage =
                totalClasses == 0
                        ? 0
                        : (totalPresent * 100.0)
                          / totalClasses;

        // 8. Create final response
        Map<String, Object> report =
                new LinkedHashMap<>();

        report.put(
                "studentId",
                student.getId()
        );

        report.put(
                "studentName",
                student.getName()
        );

        report.put(
                "rollNumber",
                student.getRollNumber()
        );

        report.put(
                "totalClasses",
                totalClasses
        );

        report.put(
                "totalPresent",
                totalPresent
        );

        report.put(
                "totalAbsent",
                totalAbsent
        );

        report.put(
                "overallPercentage",
                Math.round(overallPercentage)
        );

        report.put(
                "subjects",
                subjects
        );

        return ResponseEntity.ok(report);
    }
}