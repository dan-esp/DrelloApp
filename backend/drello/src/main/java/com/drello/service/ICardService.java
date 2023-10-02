package com.drello.service;

import org.springframework.stereotype.Service;

import com.drello.model.Attachment;
import com.drello.model.Card;
import com.drello.model.Comment;
import com.drello.model.Label;
import com.drello.model.Member;

@Service
public interface ICardService {

    Card save(Card card);

    Card update(String cardId, String title, String description, String imageCover);

    Card archive(String cardId);

    Card delete(String cardId);

    Card findByIdCard(String cardId);

    Card addLabel(String cardId, Label label);

    Card addAttachment(String cardId, Attachment attachment);

    Card addComment(String cardId, Comment comment);

    Card addAsigned(String cardId, Member member);

    Card removeAssigned(String cardId, String memberId);

    Card removeLabel(String cardId, Label label);

    Card removeComment(String cardId, String commentId);

    Card updateComment(String cardId, String commentId, String newContent);

    Card updateAttchDescription(String cardId, String attachmentId, String description);

    Card updateAttchUrl(String cardId, String attachmentId, String url);

    Card move(String cardId, String listId, Integer position);
}
