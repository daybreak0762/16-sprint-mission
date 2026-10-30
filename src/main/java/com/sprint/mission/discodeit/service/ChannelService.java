package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.*;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    ChannelResponseDto createPrivateChannel(PrivateChannelRequestDto privateChannelRequestDto);
    ChannelResponseDto createPublicChannel(PublicChannelRequestDto publicChannelRequestDto);

    ChannelResponseDto find(UUID channelId);
    List<ChannelResponseDto> findAllByUserId(UUID userId);

    ChannelResponseDto update(UUID channelId, PublicChannelUpdateDto publicChannelUpdateDto);
    void delete(UUID channelId);
}
