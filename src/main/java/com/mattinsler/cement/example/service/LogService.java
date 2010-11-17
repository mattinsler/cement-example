package com.mattinsler.cement.example.service;

import com.mattinsler.cement.mongo.model.LogEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 27, 2010
 * Time: 12:30:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface LogService {
    List<LogEntity> getLatestLogs(int count);
    List<LogEntity> getLatestLogs(int count, LogEntity.Level... levels);
    List<LogEntity> getLogs(String requestId);
    List<LogEntity> getLogs(String requestId, LogEntity.Level... levels);
}
