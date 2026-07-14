package com.am.attendancemanagement.repository;

import com.am.attendancemanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    // Find attendance of one student
    List<Attendance> findByStudentId(Long studentId);

    // Find student attendance subject-wise
    List<Attendance> findByStudentIdAndSubjectId(
            Long studentId,
            Long subjectId
    );

}