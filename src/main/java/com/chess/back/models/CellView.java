package com.chess.back.models;

import com.chess.back.entities.Color;

public record CellView(
        Integer row,
        Integer col,
        FigureView figure,
        Color color
        ) {
}

