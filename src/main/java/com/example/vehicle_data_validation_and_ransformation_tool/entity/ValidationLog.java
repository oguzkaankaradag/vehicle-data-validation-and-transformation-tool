package com.example.vehicle_data_validation_and_ransformation_tool.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ValidationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleId;

    private LocalDateTime timestamp;

    private boolean isValid;

    // Constructor, getters, and setters

    public ValidationLog() {
    }

    public ValidationLog(String vehicleId, LocalDateTime timestamp, boolean isValid) {
        this.vehicleId = vehicleId;
        this.timestamp = timestamp;
        this.isValid = isValid;
    }

    // Getters and Setters (omitted for brevity)
}
