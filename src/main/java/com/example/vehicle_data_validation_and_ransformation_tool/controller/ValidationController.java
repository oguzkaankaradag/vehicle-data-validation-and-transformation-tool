package com.example.vehicle_data_validation_and_ransformation_tool.controller;

import com.example.vehicle_data_validation_and_ransformation_tool.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validation")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping("/validate")
    public ResponseEntity<String> validateData(@RequestBody String vehicleId) {
        // Validate vehicleId
        boolean isValid = validationService.validate(vehicleId);

        if (isValid) {
            return ResponseEntity.ok("Data is valid");
        } else {
            return ResponseEntity.badRequest().body("Invalid data");
        }
    }
}
