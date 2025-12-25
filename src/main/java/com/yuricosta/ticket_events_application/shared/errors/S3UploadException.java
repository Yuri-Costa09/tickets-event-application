package com.yuricosta.ticket_events_application.shared.errors;

public class S3UploadException extends RuntimeException {
    public S3UploadException(String message) {
        super(message);
    }
}
