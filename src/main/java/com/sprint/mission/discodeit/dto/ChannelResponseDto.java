package com.sprint.mission.discodeit.dto;

import com.sprint.mission.discodeit.entity.ChannelType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChannelResponseDto(
        UUID channelId,
        ChannelType channelType,
        String channelName,
        String description,
        Instant lastMessageAt,
        List<UUID> userIds
) {}
