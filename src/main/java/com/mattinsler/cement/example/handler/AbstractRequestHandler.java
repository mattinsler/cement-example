package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.mattinsler.cement.example.service.RequestLogService;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 26, 2010
 * Time: 10:49:37 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractRequestHandler {
    protected RequestLogService requestLogService;

    @Inject
    void setDependencies(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }
}
