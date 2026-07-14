package com.am.attendancemanagement.repository;


import com.am.attendancemanagement.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface FacultyRepository
        extends JpaRepository<Faculty, Long> {


    Optional<Faculty> findByUserId(Long userId);


}