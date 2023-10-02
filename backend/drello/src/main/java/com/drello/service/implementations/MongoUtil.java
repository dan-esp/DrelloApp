package com.drello.service.implementations;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.drello.exceptions.EntityNotFoundException;
import com.mongodb.client.result.UpdateResult;

import lombok.Getter;

@Getter
public class MongoUtil {

    private MongoTemplate mongoTemplate;

    private Update update;
    private Query query;
    private Class<?> objClass;

    public MongoUtil(MongoTemplate mongoTemplate, Class<?> objClass) {
        this.mongoTemplate = mongoTemplate;
        this.objClass = objClass;
    }

    public MongoUtil setIsCriteria(String key, String value) {
        this.query = new Query(Criteria.where(key).is(value));
        this.existEntity(value, objClass);
        return this;
    }

    public MongoUtil addIsCriteria(String key, String value) {
        this.query.addCriteria(Criteria.where(key).is(value));
        return this;
    }

    public MongoUtil setInCriteria(String key, List<String> values) {
        this.query = new Query(Criteria.where(key).in(values));
        return this;
    }

    public MongoUtil addInCriteria(String key, List<String> values) {
        this.query.addCriteria(Criteria.where(key).in(values));
        return this;
    }

    public Update setIdCriteria(String value) {
        query = new Query(Criteria.where("_id").is(value));
        existEntity(value, objClass);
        this.update = new Update();
        return this.update;
    }


    public MongoUtil setIdCriteria(String key,String value) {
        query = new Query(Criteria.where(key).is(value));
        existEntity(value, objClass);
        return this;
    }

    public boolean execQuery() {
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, objClass);
        return updateResult.getModifiedCount() > 0;
    }

    public boolean execQuery(Update update) {
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, objClass);
        return updateResult.getModifiedCount() > 0;
    }

    public boolean existEntity(String id, Class<?> objClass) {
        boolean exist = mongoTemplate.exists(query, objClass);
        if (!exist) {
            throw new EntityNotFoundException(id, objClass.getName());
        }
        return exist;
    }
}
