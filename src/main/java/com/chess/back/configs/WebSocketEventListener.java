package com.chess.back.configs;

import com.chess.back.services.RedisSessionService.RedisSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    private RedisSessionService redisSessionService;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String websocketSessionId = headerAccessor.getSessionId();
        redisSessionService.removeSessionByWebSocketSession(websocketSessionId);
    }


//    @EventListener
//    public void handleWebSocketConnectListener(SessionConnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String websocketSessionId = headerAccessor.getSessionId();
//        redisSessionService.mapWebSocketToCustomSession(websocketSessionId, customSessionId);
//    }
}
