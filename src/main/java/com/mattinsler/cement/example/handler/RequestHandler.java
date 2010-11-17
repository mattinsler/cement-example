package com.mattinsler.cement.example.handler;

import com.mattinsler.cement.annotation.Contract;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.annotation.PathMapping;
import com.mattinsler.cement.example.contract.RequestLogContract;
import com.mattinsler.cement.mongo.model.RequestEntity;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 23, 2010
 * Time: 3:44:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestHandler extends AbstractRequestHandler {
    @Get
    @PathMapping("/${request_id}")
    @Contract(RequestLogContract.class)
    public RequestEntity executeGet(@Parameter("request_id") String requestId) {
        return requestLogService.getRequest(requestId);
    }
}
