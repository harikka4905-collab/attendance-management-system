package com.am.attendancemanagement.service;


import com.am.attendancemanagement.entity.Attendance;
import com.am.attendancemanagement.repository.AttendanceRepository;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class AttendanceService {



    private final AttendanceRepository attendanceRepository;



    public AttendanceService(
            AttendanceRepository attendanceRepository
    ){

        this.attendanceRepository =
                attendanceRepository;

    }



    public Attendance saveAttendance(
            Attendance attendance
    ){

        return attendanceRepository.save(attendance);

    }



    public List<Attendance> getAllAttendance(){

        return attendanceRepository.findAll();

    }

}