package com.am.attendancemanagement.Controller;

import com.am.attendancemanagement.dto.AttendanceRequest;
import com.am.attendancemanagement.entity.Attendance;
import com.am.attendancemanagement.entity.Student;
import com.am.attendancemanagement.entity.Subject;
import com.am.attendancemanagement.repository.AttendanceRepository;
import com.am.attendancemanagement.repository.StudentRepository;
import com.am.attendancemanagement.repository.SubjectRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public AttendanceController(
            AttendanceRepository attendanceRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository) {

        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAttendance(
            @RequestBody List<AttendanceRequest> requests) {

        if (requests == null || requests.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Attendance data is empty");
        }

        for (AttendanceRequest request : requests) {

            if (request.getStudentId() == null) {

                return ResponseEntity
                        .badRequest()
                        .body("Student ID is required");
            }

            if (request.getSubjectId() == null) {

                return ResponseEntity
                        .badRequest()
                        .body("Subject ID is required");
            }

            if (request.getStatus() == null ||
                    request.getStatus().isBlank()) {

                return ResponseEntity
                        .badRequest()
                        .body("Attendance status is required");
            }

            Student student = studentRepository
                    .findById(request.getStudentId())
                    .orElse(null);

            if (student == null) {

                return ResponseEntity
                        .badRequest()
                        .body(
                                "Student not found: "
                                        + request.getStudentId()
                        );
            }

            Subject subject = subjectRepository
                    .findById(request.getSubjectId())
                    .orElse(null);

            if (subject == null) {

                return ResponseEntity
                        .badRequest()
                        .body(
                                "Subject not found: "
                                        + request.getSubjectId()
                        );
            }

            Attendance attendance = new Attendance();

            attendance.setDate(LocalDate.now());

            attendance.setStatus(
                    request.getStatus().toUpperCase()
            );

            attendance.setStudent(student);

            attendance.setSubject(subject);

            attendanceRepository.save(attendance);
        }

        return ResponseEntity.ok(
                "Attendance saved successfully"
        );
    }

    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }
}