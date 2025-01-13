package com.chess.back.services.MoveService;

import com.chess.back.models.MoveMessage;

public interface MoveService {

    MoveMessage handleMove(String opponentSession, MoveMessage moveMessage);

}
