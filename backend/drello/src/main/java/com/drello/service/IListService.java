package com.drello.service;

import org.springframework.stereotype.Service;

import com.drello.model.CardItem;
import com.drello.model.ListCard;

@Service
public interface IListService {

    ListCard save(ListCard ListCard);

    ListCard updateTitle(String listId, String title);

    ListCard delete(String listId);

    ListCard findById(String listId);

    boolean removeCard(String listId, String cardId);

    boolean addCard(String listId, CardItem card);

    Integer getCardPosition(String listId, String card);

    boolean setCard(String listId, CardItem card, Integer position);

}
