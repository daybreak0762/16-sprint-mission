package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class BinaryContent {
    private UUID id;
    private Instant createdAt;

    private String fileName;
    private byte[] data;
    private String contentType;


    public BinaryContent(UUID id, Instant createdAt, String fileName, byte[] data, String contentType) {
        this.id = id;
        this.createdAt = createdAt;
        this.fileName = fileName;
        this.data = data;
        this.contentType = contentType;
    }
}
