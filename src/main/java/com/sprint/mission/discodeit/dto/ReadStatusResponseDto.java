package com.sprint.mission.discodeit.dto;

import java.util.UUID;

public record ReadStatusResponseDto(
        UUID statusId,
        UUID channelId,
        UUID userId
) {}
