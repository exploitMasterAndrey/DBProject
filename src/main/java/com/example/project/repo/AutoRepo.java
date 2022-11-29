package com.example.project.repo;

import com.example.project.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepo extends JpaRepository<Auto, Long> {
}
