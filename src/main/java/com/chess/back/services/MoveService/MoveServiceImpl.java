package com.chess.back.services.MoveService;

import com.chess.back.models.MoveMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MoveServiceImpl implements MoveService{

    private final SimpMessagingTemplate simpMessagingTemplate;


    public MoveServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public MoveMessage handleMove(String opponentSession, MoveMessage moveMessage) {
        simpMessagingTemplate.convertAndSend("/topic/player/"+opponentSession, moveMessage);
        return moveMessage;
    }
}
