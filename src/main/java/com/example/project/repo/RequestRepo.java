package com.example.project.repo;

import com.example.project.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, Long> {
}
