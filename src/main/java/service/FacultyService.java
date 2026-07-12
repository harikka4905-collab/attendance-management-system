package com.sam.attendance.service;


import com.sam.attendance.entity.Subject;
import com.sam.attendance.repository.SubjectRepository;

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