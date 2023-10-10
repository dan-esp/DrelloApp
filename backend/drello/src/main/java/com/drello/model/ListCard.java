package com.drello.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("lists")
@Data
public class ListCard {
    @Id
    private String id;

    private String title;
    private List<CardItem> cards;
    private Date createdAt;
    private Date updatedAt;

    public ListCard(String title) {
        this.title = title;
        this.cards = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public ListCard() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
