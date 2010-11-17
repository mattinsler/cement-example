package com.mattinsler.cement.example.handler;

import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.mongo.model.LogEntity;
import com.mattinsler.cement.util.CollectionUtil;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 27, 2010
 * Time: 12:54:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class LogsHandler extends AbstractLogHandler {
    @Get
    public List<LogEntity> executeGet() {
        return executeGet(20);
    }

    @Get
    public List<LogEntity> executeGet(@Parameter("count") int count) {
        return CollectionUtil.collect(logService.getLatestLogs(count));
    }
}
