package com.am.attendancemanagement.dto;

import lombok.Data;


@Data
public class StudentAttendanceDTO {

    private Long studentId;

    private String studentName;

    private String status;

}