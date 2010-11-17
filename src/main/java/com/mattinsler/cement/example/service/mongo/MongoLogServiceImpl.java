package com.mattinsler.cement.example.service.mongo;

import com.google.inject.Inject;
import com.lowereast.guiceymongo.GuiceyCollection;
import com.lowereast.guiceymongo.Order;
import com.lowereast.guiceymongo.guice.annotation.GuiceyMongoCollection;
import com.mattinsler.cement.example.service.LogService;
import com.mattinsler.cement.mongo.guice.CementMongoCollection;
import com.mattinsler.cement.mongo.model.LogEntity;
import com.mattinsler.cement.util.CollectionUtil;
import com.mongodb.BasicDBObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 27, 2010
 * Time: 12:31:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class MongoLogServiceImpl implements LogService {
    private static final int MaxItems = 100;

    private GuiceyCollection<LogEntity> _logCollection;

    @Inject
    MongoLogServiceImpl(@GuiceyMongoCollection(CementMongoCollection.Log) GuiceyCollection<LogEntity> logCollection) {
        _logCollection = logCollection;
    }

    @Override
    public List<LogEntity> getLatestLogs(int count) {
        return CollectionUtil.collect(
                _logCollection.find()
                .limit(Math.min(MaxItems, count))
                .sort(Order.by("$natural").descending())
        );
    }

    @Override
    public List<LogEntity> getLatestLogs(int count, LogEntity.Level... levels) {
        return CollectionUtil.collect(
                _logCollection.find(
                        new BasicDBObject(
                                LogEntity.LevelKey,
                                new BasicDBObject("$in", levels)
                        )
                )
                .limit(Math.min(MaxItems, count))
                .sort(Order.by("$natural").descending())
        );
    }

    @Override
    public List<LogEntity> getLogs(String requestId) {
        return CollectionUtil.collect(
                _logCollection.find(
                        new BasicDBObject(LogEntity.RequestIdKey, requestId)
                )
                .sort(Order.by("$natural").descending())
        );
    }

    @Override
    public List<LogEntity> getLogs(String requestId, LogEntity.Level... levels) {
        return CollectionUtil.collect(
                _logCollection.find(
                        new BasicDBObject(LogEntity.RequestIdKey, requestId)
                                .append(
                                        LogEntity.LevelKey,
                                        new BasicDBObject("$in", levels)
                                )
                )
                .sort(Order.by("$natural").descending())
        );
    }
}
