package com.drello.service.implementations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.Attachment;
import com.drello.model.Card;
import com.drello.model.Comment;
import com.drello.model.Label;
import com.drello.model.Member;
import com.drello.repository.CardRepository;
import com.drello.service.ICardService;
import com.mongodb.BasicDBObject;

@Service
public class CardService implements ICardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private MongoUtil mongoUtil;

    public CardService(CardRepository cardRepository, MongoTemplate mongoTemplate) {
        this.cardRepository = cardRepository;
        this.mongoTemplate = mongoTemplate;
        this.mongoUtil = new MongoUtil(mongoTemplate, Card.class);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card update(String cardId, String title, String description, String imageCover) {
        Update update = mongoUtil.setIdCriteria(cardId);

        update.set("title", title);
        update.set("description", description);
        update.set("imageCover", imageCover);
         update.set("updatedAt", new Date());

        if (!mongoUtil.execQuery())
            return null;

        return findByIdCard(cardId);
    }

    @Override
    public Card archive(String cardId) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.set("status", false);
         update.set("updatedAt", new Date());
        if (!mongoUtil.execQuery())
            return null;

        return findByIdCard(cardId);
    }

    @Override
    public Card findByIdCard(String cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException(cardId, Card.class.getName()));
    }

    @Override
    public Card addLabel(String cardId, Label label) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.push("labels", label);
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card addAttachment(String cardId, Attachment attachment) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.push("attachments", attachment);
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card addComment(String cardId, Comment comment) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.push("comments", comment);
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card addAsigned(String cardId, Member member) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.push("members", member);
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card removeAssigned(String cardId, String memberId) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.pull("members.second.userId", memberId);
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card removeLabel(String cardId, Label label) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.pull("labels", label);
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card removeComment(String cardId, String commentId) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.pull("comments", new BasicDBObject("id", commentId));
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);

    }

    @Override
    public Card updateComment(String cardId, String commentId, String newContent) {
        mongoUtil.setIdCriteria("_id", cardId).addIsCriteria(
                "comments.id", commentId);
        Update update = new Update();
        update.set("comments.$.content", newContent);
        if (!mongoUtil.execQuery(update))
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card updateAttchDescription(String cardId, String attachmentId, String description) {
        mongoUtil.setIdCriteria("_id", cardId)
                .addIsCriteria("attachments.attachmentId", attachmentId);

        Update update = new Update().set("attachments.$.description", description);
        if (!mongoUtil.execQuery(update))
            return null;

        return findByIdCard(cardId);
    }

    @Override
    public Card updateAttchUrl(String cardId, String attachmentId, String url) {
        mongoUtil.setIdCriteria("_id", cardId)
                .addIsCriteria("attachments.attachmentId", attachmentId);

        Update update = new Update().set("attachments.$.description", url);
        if (!mongoUtil.execQuery(update))
            return null;

        return findByIdCard(cardId);
    }

    @Override
    public Card move(String cardId, String listId, Integer position) {
        Update update = mongoUtil.setIdCriteria(cardId);
        update.set("listId", listId).set("position", position);
        if (!mongoUtil.execQuery())
            return null;
        return findByIdCard(cardId);
    }

    @Override
    public Card delete(String cardId) {
        Card card = findByIdCard(cardId);
        cardRepository.delete(card);
        return card;
    }

}
