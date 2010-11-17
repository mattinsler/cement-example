package com.mattinsler.cement.example.handler;

import com.mattinsler.cement.annotation.Contract;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.example.contract.RequestLogContract;
import com.mattinsler.cement.mongo.model.RequestEntity;
import com.mattinsler.cement.util.CollectionUtil;
import com.mongodb.BasicDBObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 24, 2010
 * Time: 2:02:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestsHandler extends AbstractRequestHandler {
    @Get
    @Contract(RequestLogContract.class)
    public List<RequestEntity> executeGet() {
        return executeGet(20);
    }

    @Get
    @Contract(RequestLogContract.class)
    public List<RequestEntity> executeGet(@Parameter("count") int count) {
        return CollectionUtil.collect(requestLogService.getLatestRequests(count));
    }

    @Get
    @Contract(RequestLogContract.class)
    public List<RequestEntity> executeGetLogCount(@Parameter("log_count") int logCount) {
        return executeGetLogCount(20, logCount);
    }

    @Get
    @Contract(RequestLogContract.class)
    public List<RequestEntity> executeGetLogCount(@Parameter("count") int count, @Parameter("log_count") int logCount) {
        return CollectionUtil.collect(requestLogService.getLatestRequests(count, new BasicDBObject(RequestEntity.LogCountKey, new BasicDBObject("$gte", logCount))));
    }
}
