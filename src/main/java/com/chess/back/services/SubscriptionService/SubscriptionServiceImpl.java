package com.chess.back.services.SubscriptionService;

import com.chess.back.entities.Color;
import com.chess.back.models.MatchingMessage;
import com.chess.back.services.RedisSessionService.RedisSessionService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    private final RedisSessionService redisSessionService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public SubscriptionServiceImpl(RedisSessionService redisSessionService, SimpMessagingTemplate simpMessagingTemplate) {
        this.redisSessionService = redisSessionService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public MatchingMessage handleSubscribe(String webSocketSessionId,String customSessionId) {
        var firstAvailableSession = redisSessionService.getFirstAvailableSession(customSessionId);
        System.out.println("firstAvailableSession = " + firstAvailableSession);
        System.out.println("webSocketSessionId = " + webSocketSessionId);
        System.out.println("customSessionId = " + customSessionId);
        if (firstAvailableSession != null) {
            redisSessionService.removeSessionByCustomSession(firstAvailableSession);
            MatchingMessage opponentMessage = new MatchingMessage(true, customSessionId, Color.White);
            simpMessagingTemplate.convertAndSend("/topic/player/" + firstAvailableSession, opponentMessage);
            return new MatchingMessage(true, firstAvailableSession, Color.Black);
        }

        redisSessionService.addSession(webSocketSessionId, customSessionId);
        return new MatchingMessage(false, null, null);
    }
}
