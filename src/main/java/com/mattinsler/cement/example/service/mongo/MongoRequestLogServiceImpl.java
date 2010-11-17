package com.mattinsler.cement.example.service.mongo;

import com.google.inject.Inject;
import com.lowereast.guiceymongo.GuiceyCollection;
import com.lowereast.guiceymongo.Order;
import com.lowereast.guiceymongo.guice.annotation.GuiceyMongoCollection;
import com.mattinsler.cement.example.service.RequestLogService;
import com.mattinsler.cement.mongo.guice.CementMongoCollection;
import com.mattinsler.cement.mongo.model.RequestEntity;
import com.mattinsler.cement.util.CollectionUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 26, 2010
 * Time: 10:33:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class MongoRequestLogServiceImpl implements RequestLogService {
    private static final int MaxItems = 100;

    private final GuiceyCollection<RequestEntity> _requestCollection;

    @Inject
    MongoRequestLogServiceImpl(@GuiceyMongoCollection(CementMongoCollection.Request) GuiceyCollection<RequestEntity> requestCollection) {
        _requestCollection = requestCollection;
    }

    @Override
    public List<RequestEntity> getLatestRequests(int count) {
        return CollectionUtil.collect(
                _requestCollection.find()
                .limit(Math.min(MaxItems, count))
                .sort(Order.by("$natural").descending())
        );
    }

    @Override
    public List<RequestEntity> getLatestRequests(int count, DBObject query) {
        return CollectionUtil.collect(
                _requestCollection.find(query)
                .limit(Math.min(MaxItems, count))
                .sort(Order.by("$natural").descending())
        );
    }

    @Override
    public RequestEntity getRequest(String requestId) {
        return _requestCollection.findOne(new BasicDBObject(RequestEntity.RequestIdKey, requestId));
    }
}
