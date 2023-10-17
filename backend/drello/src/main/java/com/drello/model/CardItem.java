package com.drello.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CardItem {
    private String listId;
    private String cardId;
    private String title;
    private String imgCover;
    private List<Label> labels;
    private Integer comments;
    private Integer attachments;
}
