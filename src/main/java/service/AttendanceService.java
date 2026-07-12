package com.sam.attendance.service;


import com.sam.attendance.dto.AttendanceRequest;
import com.sam.attendance.entity.Attendance;
import com.sam.attendance.repository.AttendanceRepository;

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