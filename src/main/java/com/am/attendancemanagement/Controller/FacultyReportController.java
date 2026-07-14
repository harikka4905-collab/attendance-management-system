package com.am.attendancemanagement.Controller;

import com.am.attendancemanagement.entity.Attendance;
import com.am.attendancemanagement.entity.Student;
import com.am.attendancemanagement.repository.AttendanceRepository;
import com.am.attendancemanagement.repository.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/faculty-report")
@CrossOrigin
public class FacultyReportController {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    public FacultyReportController(
            StudentRepository studentRepository,
            AttendanceRepository attendanceRepository) {

        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "Faculty Report Controller Working";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudentReports() {

        List<Student> students =
                studentRepository.findAll();

        List<Map<String, Object>> reports =
                new ArrayList<>();

        for (Student student : students) {

            List<Attendance> allAttendance =
                    attendanceRepository.findByStudentId(
                            student.getId()
                    );

            /*
             * IMPORTANT:
             * Use the same rule as StudentReportController.
             *
             * Ignore attendance records that do not have
             * a subject.
             */
            List<Attendance> attendanceList =
                    allAttendance
                            .stream()
                            .filter(attendance ->
                                    attendance.getSubject() != null
                            )
                            .toList();

            int totalClasses =
                    attendanceList.size();

            long present =
                    attendanceList
                            .stream()
                            .filter(attendance ->
                                    "PRESENT".equalsIgnoreCase(
                                            attendance.getStatus()
                                    )
                            )
                            .count();

            int absent =
                    totalClasses - (int) present;

            double percentage =
                    totalClasses == 0
                            ? 0
                            : (present * 100.0)
                              / totalClasses;

            Map<String, Object> report =
                    new LinkedHashMap<>();

            report.put(
                    "studentId",
                    student.getId()
            );

            report.put(
                    "name",
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
                    "present",
                    present
            );

            report.put(
                    "absent",
                    absent
            );

            report.put(
                    "percentage",
                    Math.round(percentage)
            );

            reports.add(report);
        }

        return ResponseEntity.ok(reports);
    }
}