package com.sam.attendance.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    @Column(unique = true)
    private String rollNumber;


    private String email;


    private String department;



    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

}