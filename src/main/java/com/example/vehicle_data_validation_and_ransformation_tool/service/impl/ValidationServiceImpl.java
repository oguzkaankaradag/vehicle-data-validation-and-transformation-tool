package com.example.vehicle_data_validation_and_ransformation_tool.service.impl;


import com.example.vehicle_data_validation_and_ransformation_tool.entity.ValidationLog;
import com.example.vehicle_data_validation_and_ransformation_tool.repository.ValidationLogRepository;
import com.example.vehicle_data_validation_and_ransformation_tool.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private ValidationLogRepository validationLogRepository;

    @Autowired
    private S3Service s3Service;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Override
    public boolean validate(String vehicleId) {
        boolean isValid = validateVehicleId(vehicleId);

        saveValidationLog(vehicleId, LocalDateTime.now(), isValid);

        if (isValid) {
            uploadValidationLogToS3(vehicleId);
        }

        return isValid;
    }

    private boolean validateVehicleId(String vehicleId) {
        return vehicleId != null && !vehicleId.isEmpty();
    }

    private void saveValidationLog(String vehicleId, LocalDateTime timestamp, boolean isValid) {
        ValidationLog validationLog = new ValidationLog(vehicleId, timestamp, isValid);
        validationLogRepository.save(validationLog);
    }

    private void uploadValidationLogToS3(String vehicleId) {
        try {
            File tempFile = createTempValidationLogFile(vehicleId);

            s3Service.uploadFileToS3("validation-logs/" + vehicleId + "-validation.log", tempFile);

            tempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createTempValidationLogFile(String vehicleId) throws IOException {
        String content = "Validation log for vehicle ID: " + vehicleId;
        File tempFile = File.createTempFile("validation-log-", ".tmp");
        FileWriter writer = new FileWriter(tempFile);
        writer.write(content);
        writer.close();
        return tempFile;
    }
}
