package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    ChannelResponseDto createPrivateChannel(PrivateChannelCreateDto privateChannelCreateDto);
    ChannelResponseDto createPublicChannel(PublicChannelCreateDto publicChannelCreateDto);

    ChannelResponseDto find(UUID channelId);
    List<ChannelResponseDto> findAllByUserId(UUID userId);

    ChannelResponseDto update(UUID channelId, PublicChannelUpdateDto publicChannelUpdateDto);
    void delete(UUID channelId);
}
