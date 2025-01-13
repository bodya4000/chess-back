package com.chess.back.entities;

public enum Color {
    White("White"),
    Black("Black");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
