package com.am.attendancemanagement.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class AttendanceDTO {

    private Long studentId;

    private Long subjectId;

    private LocalDate date;

    private String status;

}