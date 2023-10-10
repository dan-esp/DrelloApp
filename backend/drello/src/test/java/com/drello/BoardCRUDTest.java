package com.drello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.Board;
import com.drello.model.BoardPrivacy;
import com.drello.model.Member;
import com.drello.model.Pair;
import com.drello.model.UserEntity;
import com.drello.model.UserRole;
import com.drello.service.IBoardService;

@SpringBootTest
public class BoardCRUDTest {

    @Autowired
    private IBoardService boardService;

    @Test
    public String createBoardTest() {

        UserEntity userEntity = new UserEntity("daniel", "daniel@mail.com", "daniel123");

        List<Pair<UserRole, Member>> members = new ArrayList<>();
        members.add(new Pair<UserRole, Member>(UserRole.COLLABORATOR, new Member(userEntity.getId(),
                userEntity.getUsername(), userEntity.getEmail(), userEntity.getProfileUrl())));

        Board board = new Board("title", BoardPrivacy.PRIVATE, null);

        board.setMembers(members);

        board = boardService.save(board);

        return board.getId();
    }

    @Test
    public void updateBoardTest() {
        String id = createBoardTest();
        String newTitle = "new Title";
        BoardPrivacy boardPrivacy = BoardPrivacy.PUBLIC;
        String imageUrl = "http://localhost:8080";
        Board board = boardService.update(id, newTitle, boardPrivacy, imageUrl);

        assertEquals(board.getTitle(), newTitle);
        assertEquals(board.getPrivacy(), boardPrivacy);
        assertEquals(board.getImgUrl(), imageUrl);

        boardService.delete(id);
    }

    @Test
    public void updateBoardBackgroundTest() {
        String id = createBoardTest();
        String imageUrl = "badExample";
        boolean updated = boardService.updateBackground(id, imageUrl);
        Board board = boardService.findById(id);

        assertTrue(updated);
        assertEquals(board.getImgUrl(), imageUrl);
        boardService.delete(id);
    }

    @Test
    public void badUpdateBoardBackgroundTest() {
        String id = "unexistingId";
        String imageUrl = "badExample";
        assertThrows(EntityNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                boardService.updateBackground(id, imageUrl);
            }
        }, "Board with id " + id + " not found");
    }

    private String createBoardWithMembers() {
        Board board = new Board("title", BoardPrivacy.PRIVATE, null);
        board = boardService.save(board);
        String id = board.getId();
        boolean added = boardService.addMember(id, UserRole.COLLABORATOR,
                new Member("90129e1ed13", "daniel", "daniel@mail.com", "daniel123"));
        assertTrue(added);
        added = boardService.addMember(id, UserRole.GUESS,
                new Member("n1e81e93heh311", "thomas", "daniel@mail.com", "daniel123"));
        assertTrue(added);
        added = boardService.addMember(id, UserRole.OWNER,
                new Member("dqjq891391j3e13", "andresPeres", "daniel@mail.com", "daniel123"));
        assertTrue(added);
        return id;
    }

    @Test
    public void addMemberTest() {
        String id = createBoardWithMembers();
        Board board = boardService.findById(id);

        assertEquals(3, board.getMembers().size());
        List<Pair<UserRole, Member>> members = board.getMembers();
        assertEquals(members.get(0).getSecond().getUserId(), "90129e1ed13");
        assertEquals(members.get(1).getSecond().getUserId(), "n1e81e93heh311");
        assertEquals(members.get(2).getSecond().getUserId(), "dqjq891391j3e13");

        boardService.delete(id);
    }

    @Test
    public void removeMemberTest() {
        String id = createBoardWithMembers();

        Board board = boardService.findById(id);

        assertEquals(3, board.getMembers().size());
        List<Pair<UserRole, Member>> members = board.getMembers();
        assertEquals(members.get(0).getSecond().getUserId(), "90129e1ed13");
        assertEquals(members.get(1).getSecond().getUserId(), "n1e81e93heh311");
        assertEquals(members.get(2).getSecond().getUserId(), "dqjq891391j3e13");

        boolean executed = boardService.removeMember(id, "4354655432w21");

        board = boardService.findById(id);
        assertFalse(executed);
        assertEquals(3, board.getMembers().size());

        executed = boardService.removeMember(id, "dqjq891391j3e13");

        board = boardService.findById(id);
        assertTrue(executed);
        assertEquals(2, board.getMembers().size());
        boardService.delete(id);
    }

    @Test
    public void modifyRoleMemberTest() {
        String id = createBoardWithMembers();
        boolean executed = boardService.modifyRoleMember(id, "90129e1ed139ed13", UserRole.OWNER);

        Board board = boardService.findById(id);
        assertFalse(executed);
        assertEquals(UserRole.COLLABORATOR, board.getMembers().get(0).getFirst());

        executed = boardService.modifyRoleMember(id, "90129e1ed13", UserRole.OWNER);

        board = boardService.findById(id);
        assertTrue(executed);
        assertEquals(UserRole.OWNER, board.getMembers().get(0).getFirst());
        boardService.delete(id);
    }

    @Test
    public void requestUserRoleTest() {
        String id = createBoardWithMembers();

        UserRole userRole = boardService.getUserRole(id, "90129e1ed13");

        assertEquals(UserRole.COLLABORATOR, userRole);
        boardService.delete(id);
    }

    @Test
    public void badRequestUserRoleTest() {
        String id = createBoardWithMembers();

        assertThrows(EntityNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                boardService.getUserRole("6516ee0e6a3d875d4b61d5cache", "6516ee0e6a3d875d4b61d5cachecheck");
            }
        }, "Board with id " + id + " not found");
        boardService.delete(id);

    }

    @Test
    public void requestWIthNoExistingUserRoleTest() {
        String id = createBoardWithMembers();

        UserRole userRole = boardService.getUserRole(id, "90129e1sded13");

        assertNull(userRole);
        boardService.delete(id);
    }

}