package com.chess.back.services.SubscriptionService;

import com.chess.back.models.MatchingMessage;

public interface SubscriptionService {

    MatchingMessage handleSubscribe(String webSocketSessionId,String customSessionId);
}
