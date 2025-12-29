package com.yuricosta.ticket_events_application.shared.images;

import com.yuricosta.ticket_events_application.shared.errors.S3UploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

// TOOD: Implement image entity.
@Service
public class ImageStorageService {

    private final S3AsyncClient s3;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public ImageStorageService(S3AsyncClient s3) {
        this.s3 = s3;
    }

    public CompletableFuture<String> upload(byte[] data, String contentType) {

        String ext = switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png"  -> ".png";
            case "image/webp" -> ".webp";
            default -> "";
        };

        String key = "uploads/" + UUID.randomUUID() + ext;

        PutObjectRequest req = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(contentType) // JPEG, PNG, these kind of things.
                .build();

        String url = "https://" + bucketName + ".s3." + "us-east-1" + ".amazonaws.com/" + key;

        return s3.putObject(req, AsyncRequestBody.fromBytes(data))
                .thenApply(response -> url)
                .exceptionally(ex -> {;
                    throw new S3UploadException("Failed to upload image to S3");
                });
    }
}
