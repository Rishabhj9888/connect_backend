package com.example.demo.controller;

import com.example.demo.DTO.AlumniRequest;
import com.example.demo.DTO.AlumniResponse;
import com.example.demo.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;       // ✅ Correct import
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {

    @Autowired
    private AlumniService alumniService;

    // ADMIN ONLY - add alumni
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlumniResponse> addAlumni(@RequestBody AlumniRequest request) {
        return ResponseEntity.ok(alumniService.addAlumni(request));
    }

    //  - search alumni by college name
    @GetMapping("/search/{collegeName}")
    public ResponseEntity<List<AlumniResponse>> getAlumniByCollege(@PathVariable String collegeName) {
        return ResponseEntity.ok(alumniService.getAlumniByCollege(collegeName));
    }

    // ADMIN ONLY - get all alumni (pageable with default page=0, size=10)
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<AlumniResponse>> getAllAlumni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(alumniService.getAllAlumni(pageable));
    }
}
