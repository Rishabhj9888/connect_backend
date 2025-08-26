package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumni")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String company;
    private String designation;

    @Column(nullable = false)
    private String collegeName;

    private String specialization;
}
