package com.chess.back.models;

import com.chess.back.entities.Color;

public record MatchingMessage(
        boolean foundOpponent, String sessionId, Color playerColor
) {

}
