package com.drello.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drello.model.Board;
import com.drello.model.BoardPrivacy;
import com.drello.model.Member;
import com.drello.model.UserRole;

@Service
public interface IBoardService {

    Board save(Board board);

    Board update(String id, String title, BoardPrivacy boardPrivacy, String imageUrl);

    boolean updateBackground(String boardId, String imageUrl);

    boolean addMember(String idBoard, UserRole userRole, Member member);

    boolean removeMember(String boardId, String userId);

    boolean modifyRoleMember(String idBoard, String memberId, UserRole userRole);

    Board delete(String id);

    Board findById(String idBoard);

    List<Board> findBoardsById(List<String> boardIds);

    UserRole getUserRole(String idBoard, String userId);
}
