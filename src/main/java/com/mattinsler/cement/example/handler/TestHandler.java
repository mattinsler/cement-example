package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.exception.CementException;
import com.mattinsler.cement.logging.CementLogger;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 27, 2010
 * Time: 2:43:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestHandler {
    private final CementLogger _logger;

    @Inject
    TestHandler(CementLogger logger) {
        _logger = logger;
    }

    @Get
    public void executeGet() {
        _logger.info("Hello World!!!");
        throw new CementException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Hello World!!!");
    }

    @Get
    public void executeGet(@Parameter("delay") int delay) {
        _logger.info("Request processing begin");
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
        }
        _logger.info("Request processing end");
    }
}
