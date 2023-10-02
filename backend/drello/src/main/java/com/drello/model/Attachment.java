package com.drello.model;


import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment {
    private UUID uuid;
    private String attachmentFile;
    private String description;
    private Date createdAt;

    public Attachment(String attachmentFile, String description) {
        this.uuid = UUID.randomUUID();
        this.attachmentFile = attachmentFile;
        this.description = description;
        this.createdAt = new Date();
    }
}
