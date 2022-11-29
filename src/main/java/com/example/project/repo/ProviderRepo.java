package com.example.project.repo;

import com.example.project.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepo extends JpaRepository<Provider, Long> {
}
