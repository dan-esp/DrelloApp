package com.drello.service;

import com.drello.model.Comment;

public interface ICommentService {

    Comment save(Comment comment);

    boolean updateContent(String commentId, String content);

    Comment delete(String commentId);

    Comment findById(String commentId);
}
