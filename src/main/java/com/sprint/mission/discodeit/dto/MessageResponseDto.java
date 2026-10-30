package com.sprint.mission.discodeit.dto;

import java.util.List;
import java.util.UUID;

public record MessageResponseDto(
        UUID messageId,
        String content,
        UUID channelId,
        UUID authorId,
        List<UUID> attachmentIds
) {}
