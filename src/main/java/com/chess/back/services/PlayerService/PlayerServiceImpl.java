package com.chess.back.services.PlayerService;

import com.chess.back.models.MatchingMessage;
import com.chess.back.models.MoveMessage;
import com.chess.back.services.MoveService.MoveService;
import com.chess.back.services.SubscriptionService.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final SubscriptionService subscriptionService;
    private final MoveService moveService;


    public PlayerServiceImpl(SubscriptionService subscriptionService, MoveService moveService) {
        this.subscriptionService = subscriptionService;
        this.moveService = moveService;
    }

    @Override
    public MatchingMessage handleSubscribe(String customSessionId, String webSocketSessionId) {
        return subscriptionService.handleSubscribe(customSessionId, webSocketSessionId);
    }

    @Override
    public MoveMessage handleMove(String opponentSession, MoveMessage moveMessage) {
        return moveService.handleMove(opponentSession, moveMessage);
    }
}
