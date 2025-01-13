package com.chess.back.repositories;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class SessionRepository {
    private final Set<String> sessions = new HashSet<>();

    public void save(String sessionId) {
        sessions.add(sessionId);
    }

    public void delete(String sessionId) {
        sessions.remove(sessionId);
    }

    public Set<String> getAll() {
        return sessions;
    }
}
