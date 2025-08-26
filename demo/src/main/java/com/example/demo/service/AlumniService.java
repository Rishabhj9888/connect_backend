package com.example.demo.service;

import com.example.demo.DTO.AlumniRequest;
import com.example.demo.DTO.AlumniResponse;
import com.example.demo.Repository.AlumniRepository;
import com.example.demo.model.Alumni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumniService {

    @Autowired
    private AlumniRepository repository;

    // Add new alumni (ADMIN)
    public AlumniResponse addAlumni(AlumniRequest request) {
        Alumni alumni = new Alumni();
        alumni.setName(request.getName());
        alumni.setEmail(request.getEmail());
        alumni.setCompany(request.getCompany());
        alumni.setDesignation(request.getDesignation());
        alumni.setCollegeName(request.getCollegeName());
        alumni.setSpecialization(request.getSpecialization());

        Alumni saved = repository.save(alumni);

        return new AlumniResponse(
                saved.getName(),
                saved.getEmail(),
                saved.getCompany(),
                saved.getDesignation(),
                saved.getCollegeName(),
                saved.getSpecialization()
        );
    }

    // Search alumni by college name (STUDENT & ADMIN)
    public List<AlumniResponse> getAlumniByCollege(String collegeName) {
        return repository.findByCollegeNameIgnoreCase(collegeName)
                .stream()
                .map(alumni -> new AlumniResponse(
                        alumni.getName(),
                        alumni.getEmail(),
                        alumni.getCompany(),
                        alumni.getDesignation(),
                        alumni.getCollegeName(),
                        alumni.getSpecialization()
                ))
                .collect(Collectors.toList());
    }

    // Get all alumni (ADMIN only, pageable)
    public Page<AlumniResponse> getAllAlumni(Pageable pageable) {
        return repository.findAll(pageable)
                .map(alumni -> new AlumniResponse(
                        alumni.getName(),
                        alumni.getEmail(),
                        alumni.getCompany(),
                        alumni.getDesignation(),
                        alumni.getCollegeName(),
                        alumni.getSpecialization()
                ));
    }
}
