package com.drello.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("comment")
public class Comment {
    @Id
    private String id;
    private Date createdAt;
    private Date updatedAt;
    private Member author;
    private String cardId;
    private String content;

    public Comment(String content, Member author, String cardId) {
        this.content = content;
        this.author = author;
        this.cardId = cardId;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Comment() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    

}
