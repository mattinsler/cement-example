package com.mattinsler.cement.example.service;

import com.mattinsler.cement.mongo.model.RequestEntity;
import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 26, 2010
 * Time: 10:26:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RequestLogService {
    List<RequestEntity> getLatestRequests(int count);
    List<RequestEntity> getLatestRequests(int count, DBObject query);
    RequestEntity getRequest(String requestId);
}
