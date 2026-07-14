package com.am.attendancemanagement.service;


import com.am.attendancemanagement.entity.Attendance;
import com.am.attendancemanagement.repository.AttendanceRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {


    private final AttendanceRepository attendanceRepository;


    public StudentService(
            AttendanceRepository attendanceRepository
    ){

        this.attendanceRepository = attendanceRepository;

    }



    public List<Attendance> getStudentAttendance(
            Long studentId
    ){

        return attendanceRepository
                .findByStudentId(studentId);

    }



    public double calculateAttendancePercentage(
            Long studentId
    ){


        List<Attendance> attendanceList =
                attendanceRepository
                        .findByStudentId(studentId);



        if(attendanceList.isEmpty()){

            return 0;

        }



        long presentCount =
                attendanceList.stream()
                        .filter(a ->
                                a.getStatus()
                                        .equals("PRESENT")
                        )
                        .count();



        return (presentCount * 100.0)
                / attendanceList.size();

    }

}