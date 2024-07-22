package com.example.vehicle_data_validation_and_ransformation_tool.repository;

import com.example.vehicle_data_validation_and_ransformation_tool.entity.ValidationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationLogRepository extends JpaRepository<ValidationLog, Long> {
    // Custom queries if needed
}
