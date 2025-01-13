package com.chess.back.controllers;

import com.chess.back.models.MatchingMessage;
import com.chess.back.models.MoveMessage;
import com.chess.back.services.MoveService.MoveService;
import com.chess.back.services.MoveService.MoveServiceImpl;
import com.chess.back.services.PlayerService.PlayerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ChessController {

    private final PlayerServiceImpl playerService;
    private final MoveService moveService;


    public ChessController(PlayerServiceImpl playerService, MoveServiceImpl moveService) {
        this.playerService = playerService;
        this.moveService = moveService;
    }

    @MessageMapping("/player/{sessionId}")
    @SendTo("/topic/player/{sessionId}")
    public MatchingMessage handlePlayerSubscription(@DestinationVariable String sessionId, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("establishment message");
        return playerService.handleSubscribe(headerAccessor.getSessionId(),sessionId);
    }

    @MessageMapping("/player/move/{opponentSessionId}")
    @SendTo("/topic/player/{opponentSessionId}")
    public void handlePlayerMove(@DestinationVariable String opponentSessionId, @Payload MoveMessage moveMessage) {
         moveService.handleMove(opponentSessionId,moveMessage);
    }

//    @MessageMapping("/game/{sessionId}/move")
//    @SendTo("/topic/game/{sessionId}/move")
//    public MoveMessage handlePlayerMove(@DestinationVariable String sessionId, @Payload MoveMessage moveMessage) {
//        return playerService.processMove(sessionId, moveMessage);
//    }

}
