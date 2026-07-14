package com.am.attendancemanagement.service;


import com.am.attendancemanagement.entity.Subject;
import com.am.attendancemanagement.repository.SubjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class FacultyService {



    private final SubjectRepository subjectRepository;



    public FacultyService(
            SubjectRepository subjectRepository
    ){

        this.subjectRepository = subjectRepository;

    }



    public List<Subject> getFacultySubjects(
            Long facultyId
    ){


        return subjectRepository
                .findByFacultyId(facultyId);

    }

}