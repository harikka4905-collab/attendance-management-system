package com.sam.attendance.dto;

import lombok.Data;


@Data
public class StudentAttendanceDTO {

    private Long studentId;

    private String studentName;

    private String status;

}