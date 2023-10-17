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
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private List<Label> labels;
    private List<Attachment> attachments;
    private List<Comment> comments;
    private List<Member> members;

    public Card( String description) {
        this.description = description;
        this.comments = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.members = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Card() {
        this.comments = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.members = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
