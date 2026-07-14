package com.am.attendancemanagement.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Faculty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String email;


    private String department;



    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

}