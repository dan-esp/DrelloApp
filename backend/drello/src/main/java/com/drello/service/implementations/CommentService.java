package com.drello.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.Comment;
import com.drello.repository.CommentRepository;
import com.drello.service.ICommentService;

public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public boolean updateContent(String commentId, String content) {
        Comment comment = findById(commentId);
        comment.setContent(content);
        return commentRepository.save(comment) != null;
        
    }

    @Override
    public Comment delete(String commentId) {
        Comment comment = findById(commentId);
        commentRepository.delete(comment);
        return comment;
    }

    @Override
    public Comment findById(String commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(commentId, commentId));
    }

}
