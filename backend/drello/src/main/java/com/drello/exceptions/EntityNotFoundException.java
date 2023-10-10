package com.drello.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityId, String entity) {
        super(String.format("%s entity with Id %s not found", entity, entityId));
    }
}
