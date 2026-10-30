package com.sprint.mission.discodeit.dto;

public record BinaryContentRequestDto(
        String fileName,
        String contentType,
        byte[] data
) {}