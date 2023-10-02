package com.drello.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("board")
@Data
public class Board {
    @Id
    private String id;
    private String title;
    private BoardPrivacy privacy;
    private String imgUrl;
    private Date createdAt;
    private Date updatedAt;
    private List<Pair<UserRole, Member>> members;
    private List<String> lists;
    private List<Label> configuredLabels;

    public Board(String title, BoardPrivacy boardPrivacy, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.privacy = boardPrivacy;
        this.lists = new ArrayList<>();
        this.configuredLabels = new ArrayList<>();
        this.members = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Board() {
        this.lists = new ArrayList<>();
        this.configuredLabels = new ArrayList<>();
        this.members = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}
