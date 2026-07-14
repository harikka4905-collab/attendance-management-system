package com.am.attendancemanagement.Controller;


import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/faculty")
@CrossOrigin
public class FacultyController {



    @GetMapping("/dashboard")
    public String facultyDashboard(){

        return "Faculty Dashboard";

    }



    @GetMapping("/students")
    public String getStudents(){

        return "List of Students";

    }



    @PostMapping("/mark-attendance")
    public String markAttendance(){

        return "Attendance Marked Successfully";

    }

}