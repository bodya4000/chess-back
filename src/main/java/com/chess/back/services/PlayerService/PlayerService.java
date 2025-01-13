package com.chess.back.services.PlayerService;

import com.chess.back.services.MoveService.MoveService;
import com.chess.back.services.SubscriptionService.SubscriptionService;

interface PlayerService extends SubscriptionService, MoveService {}
