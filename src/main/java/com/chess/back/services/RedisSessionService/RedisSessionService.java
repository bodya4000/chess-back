package com.chess.back.services.RedisSessionService;

import com.chess.back.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RedisSessionService {

    private static final String SESSIONS_KEY = "player_sessions";
    private final ConcurrentHashMap<String, String> webSocketToCustomSessionMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> customSessionToWebSocketMap = new ConcurrentHashMap<>();
    private final SessionRepository sessionRepository;

    public RedisSessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    public void addSession(String webSocketSession, String customSession) {
        webSocketToCustomSessionMap.put(webSocketSession, customSession);
        customSessionToWebSocketMap.put(customSession,webSocketSession);
        sessionRepository.save(customSession);
    }

    public void removeSessionByWebSocketSession(String webSocketSession) {
        String customSession = webSocketToCustomSessionMap.get(webSocketSession);
        webSocketToCustomSessionMap.remove(webSocketSession);
        customSessionToWebSocketMap.remove(customSession);
        sessionRepository.delete(customSession);

    }
    public void removeSessionByCustomSession(String customSession) {
        String webSocketSession = customSessionToWebSocketMap.get(customSession);
        webSocketToCustomSessionMap.remove(webSocketSession);
        customSessionToWebSocketMap.remove(customSession);
        sessionRepository.delete(customSession);
    }


    public String getFirstAvailableSession(String exceptSessionId) {
        var sessions = sessionRepository.getAll()
                .stream().filter(s -> !s.equals(exceptSessionId)).toList();
        if (sessions.isEmpty()) return null;
        return sessions.stream().findFirst().orElseThrow();
    }
}

