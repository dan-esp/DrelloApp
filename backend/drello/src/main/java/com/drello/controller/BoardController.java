package com.drello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drello.model.Board;
import com.drello.service.implementations.BoardService;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Board board) {
        Board boardSaved = boardService.save(board);
        return ResponseEntity.ok("Successfully operation");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> findById(@PathVariable String id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok().body(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable String id, @RequestBody Board board) {
        Board boardSaved = boardService.update(id, board.getTitle(), board.getPrivacy(), board.getImgUrl());
        return ResponseEntity.ok().body(boardSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable String id) {
        Board board = boardService.delete(id);
        return ResponseEntity.ok().body(board);
    }

    


}
