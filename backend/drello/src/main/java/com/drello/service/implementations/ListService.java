package com.drello.service.implementations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.CardItem;
import com.drello.model.ListCard;
import com.drello.repository.ListRepository;
import com.drello.service.IListService;

@Service
public class ListService implements IListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private MongoUtil mongoUtil;

    public ListService(ListRepository listRepository, MongoTemplate mongoTemplate) {
        this.listRepository = listRepository;
        this.mongoTemplate = mongoTemplate;
        this.mongoUtil = new MongoUtil(mongoTemplate, ListCard.class);
    }

    @Override
    public ListCard save(ListCard listCard) {
        return listRepository.save(listCard);
    }

    @Override
    public ListCard updateTitle(String listId, String title) {
        Update update = mongoUtil.setIdCriteria(listId);
        update.set("title", title);
        update.set("updatedAt", new Date());
        if (!mongoUtil.execQuery())
            return null;
        return findById(listId);
    }

    @Override
    public ListCard delete(String listId) {
        ListCard listCard = findById(listId);
        listRepository.delete(listCard);
        return listCard;
    }

    @Override
    public ListCard findById(String listId) {
        return listRepository.findById(listId)
                .orElseThrow(() -> new EntityNotFoundException(listId, listId));
    }

    @Override
    public boolean removeCard(String listId, String cardId) {
         Update update = mongoUtil.setIdCriteria(listId);
        update.pull("cards", new Query(Criteria.where("cardId").is(cardId)));
        return mongoUtil.execQuery();
    }

    @Override
    public boolean addCard(String listId, CardItem card) {
        Update update = mongoUtil.setIdCriteria(listId);
        update.push("cards", card);
        update.set("updatedAt", new Date());
        return mongoUtil.execQuery();
    }

    @Override
    public Integer getCardPosition(String listId, String cardId) {
        ListCard listCard = findById(listId);
        int index = 0;
        for (CardItem cards : listCard.getCards()) {
            if (cards.getCardId().equals(cardId)) {
                return index;
            }
            index++;
        }
        return index;
    }

    @Override
    public boolean setCard(String listId, CardItem card, Integer postition) {
        Update update = mongoUtil.setIdCriteria(listId);
        update.push("cards").atPosition(postition).value(card);
        return mongoUtil.execQuery();
    }
}
