package com.sprint.mission.discodeit.dto;

import java.util.UUID;

public record ReadStatusRequestDto(
        UUID channelId,
        UUID userId
) {}
