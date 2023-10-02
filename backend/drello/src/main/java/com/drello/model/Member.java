package com.drello.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private String userId;
    private String username;
    private String email;
    private String imageUrl;
}
