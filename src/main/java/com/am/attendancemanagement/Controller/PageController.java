package com.am.attendancemanagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // ADMIN
    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    // FACULTY
    @GetMapping("/faculty-dashboard")
    public String facultyDashboard() {
        return "faculty-dashboard";
    }

    @GetMapping("/faculty-reports")
    public String facultyReports() {
        return "faculty-reports";
    }

    // STUDENT
    @GetMapping("/student-dashboard")
    public String studentDashboard() {
        return "student-dashboard";
    }

    @GetMapping("/student-attendance")
    public String studentAttendance() {
        return "student-attendance";
    }

    @GetMapping("/student-subjects")
    public String studentSubjects() {
        return "student-subjects";
    }

    @GetMapping("/student-reports")
    public String studentReports() {
        return "student-reports";
    }
}