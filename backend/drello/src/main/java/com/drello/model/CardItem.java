package com.drello.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CardItem {
    private String cardId;
    private String title;
    private List<Label> labels;
}
