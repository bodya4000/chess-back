package com.chess.back.entities;

import com.chess.back.models.CellView;

import java.util.List;

public class Game {
    private static Integer idCounter;
    private final Integer id;
    private final List<List<CellView>> cells;
    private final boolean isCheck;
    private final boolean isMate;
    private final Color turn;
    private final String blackId;
    private final String whiteId;

    public Game(Integer id, List<List<CellView>> cells, boolean isCheck, boolean isMate, Color turn, String blackId, String whiteId) {
        this.id = idCounter++;
        this.cells = cells;
        this.isCheck = isCheck;
        this.isMate = isMate;
        this.turn = turn;
        this.blackId = blackId;
        this.whiteId = whiteId;
    }

    public static Integer getIdCounter() {
        return idCounter;
    }

    public Integer getId() {
        return id;
    }

    public List<List<CellView>> getCells() {
        return cells;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public boolean isMate() {
        return isMate;
    }

    public Color getTurn() {
        return turn;
    }

    public String getBlackId() {
        return blackId;
    }

    public String getWhiteId() {
        return whiteId;
    }
}
