package com.drello.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("card")
@Data
public class Card {
    @Id
    private String id;
    private String title;
    private String description;
    private String listId;
    private String listTitle;
    private String imageCover;
    private Date createdAt;
    private Date updatedAt;
    private List<Label> labels;
    private List<Attachment> attachments;
    private List<Comment> comments;
    private List<Member> members;
    private boolean status;

    public Card(String title, String description, String listId, String listTitle, String imageCover) {
        this.title = title;
        this.description = description;
        this.listId = listId;
        this.listTitle = listTitle;
        this.imageCover = imageCover;
        this.comments = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.members = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.status = true;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Card() {
        this.comments = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.members = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.status = true;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
