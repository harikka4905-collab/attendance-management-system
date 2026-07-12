package com.am.attendancemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sam.attendance")
@EnableJpaRepositories(basePackages = "com.sam.attendance.repository")
@EntityScan(basePackages = "com.sam.attendance.entity")
public class AttendanceManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                AttendanceManagementApplication.class,
                args
        );
    }
}