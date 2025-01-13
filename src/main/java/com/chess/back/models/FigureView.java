package com.chess.back.models;

import com.chess.back.entities.Color;
import com.chess.back.entities.Figures;

public record FigureView (
        Integer id,
        Color color,
        Figures type
){
}
