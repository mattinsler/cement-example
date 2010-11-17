package com.mattinsler.cement.example.handler;

import com.mattinsler.cement.annotation.Contract;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.annotation.PathMapping;
import com.mattinsler.cement.example.contract.LogContract;
import com.mattinsler.cement.mongo.model.LogEntity;
import com.mattinsler.cement.util.CollectionUtil;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 27, 2010
 * Time: 12:48:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class LogHandler extends AbstractLogHandler {
    @Get
    @PathMapping("/${request_id}")
    @Contract(LogContract.class)
    List<LogEntity> executeGet(@Parameter("request_id") String requestId) {
        return CollectionUtil.collect(logService.getLogs(requestId));
    }
}
