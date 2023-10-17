package com.drello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.drello.controller.records.board.BoardRequest;

import com.drello.model.Board;
import com.drello.model.UserEntity;
import com.drello.model.UserRole;
import com.drello.service.IBoardService;
import com.drello.service.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/boards")
@AllArgsConstructor
public class BoardController {
    @Autowired
    private IBoardService boardService;
    @Autowired
    private IUserService userService;

    @PostMapping("")
    public ResponseEntity<Board> save(@RequestBody BoardRequest boardRequest) {
        System.out.println(boardRequest);
        Board boardSaved = boardService.save(boardRequest.board());
        UserEntity userEntity = userService.findById(boardRequest.ownerId());

        userService.addBoard(boardRequest.ownerId(), boardSaved.getId());
        boardService.addMember(boardSaved.getId(), UserRole.OWNER, userEntity.toMember());

        return new ResponseEntity<Board>(boardSaved, HttpStatus.CREATED);
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Board>> getUserBoards(@PathVariable String userId) {
        UserEntity userEntity = userService.findById(userId);
        List<Board> boards = boardService.findBoardsById(userEntity.getBoards());
        return ResponseEntity.ok().body(boards);
    }

}
