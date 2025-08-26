package com.example.demo.Repository;

import com.example.demo.model.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    List<Alumni> findByCollegeNameIgnoreCase(String collegeName);
}
