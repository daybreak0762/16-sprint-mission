package com.sprint.mission.discodeit.dto;

import java.util.UUID;

public record BinaryContentResponseDto(
        UUID id,
        String fileName,
        byte[] data,
        String contentType
) {}
