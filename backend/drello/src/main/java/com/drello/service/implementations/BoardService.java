package com.drello.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.Board;
import com.drello.model.BoardPrivacy;
import com.drello.model.Member;
import com.drello.model.Pair;
import com.drello.model.UserRole;
import com.drello.repository.BoardRepository;
import com.drello.service.IBoardService;

@Service
public class BoardService implements IBoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private MongoUtil mongoUtil;

    public BoardService(BoardRepository boardRepository, MongoTemplate mongoTemplate) {
        this.boardRepository = boardRepository;
        this.mongoTemplate = mongoTemplate;
        this.mongoUtil = new MongoUtil(mongoTemplate, Board.class);
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board update(String id, String title, BoardPrivacy boardPrivacy, String imageUrl) {

        Update update = mongoUtil.setIdCriteria(id);

        update.set("title", title)
                .set("privacy", boardPrivacy)
                .set("imgUrl", imageUrl)
                .set("updatedAt", new Date());

        mongoUtil.execQuery();

        return findById(id);
    }

    @Override
    public boolean updateBackground(String boardId, String imageUrl) {
        Update update = mongoUtil.setIdCriteria(boardId);
        update.set("imgUrl", imageUrl)
                .set("updatedAt", new Date());
        return mongoUtil.execQuery();
    }

    @Override
    public boolean addMember(String boardId, UserRole userRole, Member member) {
        Update update = mongoUtil.setIdCriteria(boardId);
        update.push("members", new Pair<>(userRole, member));
        return mongoUtil.execQuery();
    }

    @Override
    public boolean removeMember(String boardId, String userId) {
        Update update = mongoUtil.setIdCriteria(boardId);
        update.pull("members", new Query(Criteria.where("second.userId").is(userId)));
        return mongoUtil.execQuery();
    }

    @Override
    public boolean modifyRoleMember(String boardId, String memberId, UserRole userRole) {
        mongoUtil.setIdCriteria("_id", boardId).addIsCriteria("members.second.userId", memberId);
        Update update = new Update().set("members.$.first", userRole);
        return mongoUtil.execQuery(update);
    }

    @Override
    public Board delete(String id) {
        Board board = findById(id);
        boardRepository.deleteById(id);
        return board;
    }

    @Override
    public Board findById(String idBoard) {
        return boardRepository.findById(idBoard)
                .orElseThrow(() -> new EntityNotFoundException(idBoard, Board.class.getName()));
    }

    @Override
    public List<Board> findBoardsById(List<String> boardIds) {
        Query query = new Query(Criteria.where("_id").in(boardIds));
        List<Board> boards = mongoTemplate.find(query, Board.class);
        return boards;
    }

    @Override
    public UserRole getUserRole(String idBoard, String userId) {
        Board board = findById(idBoard);
        List<Pair<UserRole, Member>> members = board.getMembers();
        for (Pair<UserRole, Member> member : members) {
            if (member.getSecond().getUserId().equals(userId)) {
                return member.getFirst();
            }
        }
        return null;
    }

}
