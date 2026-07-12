package com.sam.attendance.service;


import com.sam.attendance.entity.Attendance;
import com.sam.attendance.repository.AttendanceRepository;

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