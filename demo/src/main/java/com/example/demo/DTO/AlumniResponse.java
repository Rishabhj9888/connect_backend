package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlumniResponse {
    private String name;
    private String email;
    private String company;
    private String designation;
    private String collegeName;
    private String specialization;
}
