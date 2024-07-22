package com.example.vehicle_data_validation_and_ransformation_tool.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public void uploadFileToS3(String key, File file) {
        s3Client.putObject(new PutObjectRequest(bucketName, key, file));
    }
}
