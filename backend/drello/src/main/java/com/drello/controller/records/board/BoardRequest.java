package com.drello.controller.records.board;

import com.drello.model.Board;

public record BoardRequest(Board board, String ownerId) {
    
}
